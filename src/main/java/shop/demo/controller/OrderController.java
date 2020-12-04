package shop.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.config.UserLoginToken;
import shop.demo.entity.CodeMsg;
import shop.demo.entity.Order;
import shop.demo.entity.Result;
import shop.demo.service.CartService;
import shop.demo.service.OrderService;
import shop.demo.utils.TokenUtil;
import shop.demo.utils.Uuid;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private CartService cartService;

    /**
     * 生成订单
     *
     * @param paymentLimitTime int 支付限制时间（分钟）超过则自动关闭 默认60分钟
     * @param type             * int 下单方式 0-直接下单 1-购物车下单
     * @param closeLimitTime   int 订单关闭时间（分钟），超过即自动完成 默认720分钟
     * @Param orderId * String  订单id
     * @Param account * String  账号
     * @Param addressId * int  地址表id
     * @Param orderStatus * int  订单状态 0-未支付 1-已支付 2-已取消 3-交易完成
     * @Param totalPrice * BigDecimal  总金额
     * @Param info * String  订单商品具体信息
     */
    @UserLoginToken
    @PostMapping("order/addOrder")
    public Result<Object> addOrder(@RequestParam Integer addressId,
                                   @RequestParam BigDecimal totalPrice, @RequestParam String info,
                                   @RequestParam(required = false, defaultValue = "60") Integer paymentLimitTime,
                                   @RequestParam Integer type,
                                   @RequestParam(required = false, defaultValue = "720") Integer closeLimitTime) {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        String orderId = Uuid.uuid();

        int row = orderService.addOrder(orderId, account, addressId, totalPrice, info, paymentLimitTime);
        if (row == 0) {
            return Result.error(CodeMsg.FAIL, "订单生成失败");
        }

        if (type == 1) {
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
     * @param orderStatus int 订单状态 0-未支付 1-已支付 2-交易完成 3-已取消
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
}
