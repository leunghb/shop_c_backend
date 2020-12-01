package shop.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.entity.CodeMsg;
import shop.demo.entity.Result;
import shop.demo.service.OrderService;
import shop.demo.utils.TokenUtil;
import shop.demo.utils.Uuid;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 生成订单
     *
     * @Param orderId * String  订单id
     * @Param account * String  账号
     * @Param addressId * int  地址表id
     * @Param orderStatus * int  0-未付款 1-已付款
     * @Param totalPrice * BigDecimal  总金额
     * @Param info * String  订单商品具体信息
     */
    @PostMapping("order/addOrder")
    public Result<Object> addOrder(@RequestParam Integer addressId,
                                   @RequestParam BigDecimal totalPrice, @RequestParam String cartId) {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        String orderId = Uuid.uuid();
        int row = orderService.addOrder(orderId, account, addressId, totalPrice, cartId);
        if (row == 0) {
            return Result.error(CodeMsg.FAIL, "订单生成失败");
        }
        return Result.success(CodeMsg.SUCCESS, "订单生成成功");
    }
}
