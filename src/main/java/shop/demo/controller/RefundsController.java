package shop.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.entity.CodeMsg;
import shop.demo.entity.Result;
import shop.demo.service.RefundsService;
import shop.demo.utils.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@RestController
public class RefundsController {
    @Autowired
    private RefundsService refundsService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 买家申请退款/退货退款
     *
     * @param orderId     * String 订单id
     * @param type        * Integer 0-退款  1-退货退款
     * @param applyTime   * Timestamp 用户申请时间
     * @param cause       * String 申请原因
     * @param description String 原因补充
     * @param image       String 图片
     */
    @PostMapping("refunds/addRefunds")
    public Result<Object> addRefunds(@RequestParam String orderId,
                                     @RequestParam Integer type,
                                     @RequestParam Timestamp applyTime,
                                     @RequestParam String cause,
                                     @RequestParam(required = false, defaultValue = "") String description,
                                     @RequestParam(required = false, defaultValue = "") String image) {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        int row = refundsService.addRefunds(orderId, account, type, applyTime, cause, description, image);
        if (row == 0) {
            return Result.error(CodeMsg.FAIL, "申请失败");
        }
        return Result.success();
    }
}
