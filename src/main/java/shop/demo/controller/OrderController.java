package shop.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.config.UserLoginToken;
import shop.demo.entity.CodeMsg;
import shop.demo.entity.Order;
import shop.demo.entity.Result;
import shop.demo.service.*;
import shop.demo.utils.TokenUtil;
import shop.demo.utils.Uuid;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserBalanceRecordService userBalanceRecordService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsSpecsService goodsSpecsService;
    @Autowired
    private UserService userService;

    /**
     * 生成订单
     *
     * @param paymentLimitTime int 支付限制时间（分钟）超过则自动关闭 默认60分钟
     * @param type             * int 下单方式 0-直接下单 1-购物车下单
     * @param closeLimitTime   int 订单关闭（收货）时间（分钟），超过即自动完成 默认720分钟
     * @Param orderId * String  订单id
     * @Param account * String  账号
     * @Param addressId * int  地址表id
     * @Param orderStatus * int  订单状态 0-未支付 1-已支付 2-交易完成 3-已取消 4-退款中 5-退货退款中 6-已退款 7-已退货退款
     * @Param totalPrice * BigDecimal  总金额
     * @Param info * String  订单商品具体信息
     */
    @UserLoginToken
    @PostMapping("order/addOrder")
    public Result<Object> addOrder(@RequestParam Integer addressId,
                                   @RequestParam BigDecimal totalPrice, @RequestParam String info,
                                   @RequestParam(required = false, defaultValue = "60") Integer paymentLimitTime,
                                   @RequestParam Integer type,
                                   @RequestParam(required = false, defaultValue = "1440") Integer closeLimitTime) {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        String orderId = Uuid.uuid();

        int row = orderService.addOrder(orderId, account, addressId, totalPrice, info, paymentLimitTime);
        if (row == 0) {
            return Result.error(CodeMsg.FAIL, "订单生成失败");
        }

        if (type == 1) { //购物车下单
            int cartId = 0, number = 0;
            JSONArray jsonArray = JSONObject.parseArray(info);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                System.out.println(jsonObject);
                cartId = (int) jsonObject.get("cartId");
                number = (int) jsonObject.get("numberOfpurchases");
            }
            //生成订单后减少购物车对应商品添加数目
            cartService.putCartGoodsNumber(cartId, number);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        return Result.success(map);
    }

    /**
     * 订单列表
     *
     * @param account     * string
     * @param orderStatus int null-全部 0-未支付 1-已支付 2-已完成 3-退货/退款中 4-已退货/退款
     *                        (订单状态 0-未支付 1-已支付 2-交易完成 3-已取消 4-退款中 5-退货退款中 6-已退款 7-已退货退款)
     * @param limit       int
     * @param page        int
     */
    @UserLoginToken
    @PostMapping("order/getOrderList")
    public Result<Object> getOrderList(@RequestParam(required = false, defaultValue = "") Integer orderStatus,
                                       @RequestParam(required = false, defaultValue = "") Integer limit,
                                       @RequestParam(required = false, defaultValue = "") Integer page) {
        if (page != null) {
            page = (page - 1) * limit;
        }
        String account = TokenUtil.getJwtToken(httpServletRequest);
        List<Order> list = orderService.getOrderList(account, orderStatus, limit, page);
        return Result.success(list);
    }

    /**
     * 订单详情
     *
     * @param orderId * string
     */
    @UserLoginToken
    @PostMapping("order/getOrderDetail")
    public Result<Object> getOrderDetail(@RequestParam String orderId) {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        Order order = orderService.getOrder(account, orderId);
        return Result.success(order);
    }

    /**
     * 支付 （扣除余额、生成消费记录、扣除库存, 订单状态）
     *
     * @param orderId   * string 订单id
     * @param addressId Int 地址id
     */
    @UserLoginToken
    @PostMapping("order/pay")
    public Result<Object> pay(@RequestParam String orderId,
                              @RequestParam(required = false, defaultValue = "") Integer addressId) {
        String account = TokenUtil.getJwtToken(httpServletRequest);

        Order order = orderService.getOrder(account, orderId);
        BigDecimal orderPrice = order.getTotalPrice();

        BigDecimal userBalance = userService.getUserBalance(account);
        if (userBalance.compareTo(orderPrice) == -1) {
            return Result.error(CodeMsg.FAIL, "余额不足");
        }

        if (addressId != null && !order.getAddressId().equals(addressId)) {
            orderService.putOrderAddress(account, orderId, addressId);
        }

        BigDecimal totalPrice = order.getTotalPrice();
        JSONArray jsonArray = JSONArray.parseArray(order.getInfo());
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int goodsSpecsId = (int) jsonObject.get("goodsSpecsId");
            int numberOfpurchases = (int) jsonObject.get("numberOfpurchases");
            String goodsId = (String) jsonObject.get("goodsId");
            userBalanceRecordService.addUserBalanceRecord(account, 1, totalPrice, orderId); //生成消费记录
            userService.putUserBalance(account, totalPrice); //扣除用户余额
            orderService.putOrderStatus(account, orderId, 1); //订单改为已支付
            goodsService.putGoodsStock(goodsId, numberOfpurchases); //扣除商品总库存
            goodsSpecsService.putGoodsSpecsStock(goodsSpecsId, numberOfpurchases);
        }

        return Result.success();
    }

    /**
     * 修改订单状态
     *
     * @param type    * String (cancelOrder、closeOrder)
     * @param orderId * String
     */
    @UserLoginToken
    @PostMapping({"order/putOrderStatus/{type}"})
    public Result<Object> putOrderStatus(@PathVariable String type, @RequestParam String orderId) {
        String account = TokenUtil.getJwtToken(httpServletRequest);

        Integer orderStatus = 0;
        if (type.equals("cancelOrder")) {
            orderStatus = 3;
        } else if (type.equals("closeOrder")) {
            orderStatus = 2;
        }
        int row = orderService.putOrderStatus(account, orderId, orderStatus);
        if (row == 0) {
            return Result.error(CodeMsg.FAIL, "订单状态修改失败");
        }
        return Result.success(CodeMsg.SUCCESS, "订单状态修改成功");
    }
}
