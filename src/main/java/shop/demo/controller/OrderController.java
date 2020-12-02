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
     * @Param orderId * String  订单id
     * @Param account * String  账号
     * @Param addressId * int  地址表id
     * @Param orderStatus * int  订单状态 0-未支付 1-已支付 2-已取消 3-交易完成 4-退款中 5-退货退款中 6-退款完成 7-退货退款完成
     * @Param totalPrice * BigDecimal  总金额
     * @Param info * String  订单商品具体信息
     */
    @UserLoginToken
    @PostMapping("order/addOrder")
    public Result<Object> addOrder(@RequestParam Integer addressId,
                                   @RequestParam BigDecimal totalPrice, @RequestParam String info,
                                   @RequestParam(required = false, defaultValue = "60") Integer paymentLimitTime) {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        String orderId = Uuid.uuid();

        int cartId = 0, number = 0;
        JSONArray jsonArray = JSONObject.parseArray(info);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            System.out.println(jsonObject);
            cartId = (int) jsonObject.get("cartId");
            number = (int) jsonObject.get("numberOfpurchases");
        }

        int row = orderService.addOrder(orderId, account, addressId, totalPrice, info, paymentLimitTime);
        if (row == 0) {
            return Result.error(CodeMsg.FAIL, "订单生成失败");
        }
        //生成订单后减少购物车对应商品添加数目
        cartService.putCartGoodsNumber(cartId, number);
        HashMap<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        return Result.success(map);
    }
}
