package shop.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import shop.demo.config.UserLoginToken;
import shop.demo.entity.CodeMsg;
import shop.demo.entity.Refunds;
import shop.demo.entity.Result;
import shop.demo.service.OrderService;
import shop.demo.service.RefundsService;
import shop.demo.utils.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.Date;

@RestController
public class RefundsController {
    @Autowired
    private RefundsService refundsService;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private UploadController uploadController;
    @Autowired
    OrderService orderService;

    /**
     * 买家申请退款/退货退款
     *
     * @param orderId     * String 订单id
     * @param type        * Integer 0-退款  1-退货退款
     * @param cause       * String 申请原因
     * @param description String 原因补充
     * @param image       file 图片
     */
    @UserLoginToken
    @PostMapping("refunds/addRefunds")
    public Result<Object> addRefunds(@RequestParam String orderId,
                                     @RequestParam Integer type,
                                     @RequestParam String cause,
                                     @RequestParam(required = false, defaultValue = "") String description,
                                     @RequestParam(value = "file", required = false, defaultValue = "") MultipartFile[] image) throws FileNotFoundException {
        String account = TokenUtil.getJwtToken(httpServletRequest);

        String imageUrl = "";
        if (type.equals(1)) {
            for (int i = 0; i < image.length; i++) {
                Result<Object> object = uploadController.uploadSinglePicture(image[i], 1);
                imageUrl += object.getData() + ",";
            }
            imageUrl = imageUrl.substring(0, imageUrl.length() - 1);
        }
        int row = refundsService.addRefunds(orderId, account, type, cause, description, imageUrl);
        if (row == 0) {
            return Result.error(CodeMsg.FAIL, "申请失败");
        }
        orderService.putOrderStatus(account, orderId, type == 0 ? 4 : 5);
        return Result.success();
    }

    /**
     * 获取退款退货记录
     *
     * @param orderId * string 订单id
     */
    @UserLoginToken
    @PostMapping("refunds/getRefunds")
    public Result<Object> getRefunds(@RequestParam String orderId) {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        Refunds refunds = refundsService.getRefunds(account, orderId);
        if (refunds == null) {
            return Result.success(CodeMsg.NOT_FIND_DATA);
        }
        return Result.success(refunds);
    }

    /**
     * 取消退货退款
     *
     * @param orderId     * string
     * @param refundsType * int 记录类型   0-退款  1-退货退款
     */
    @UserLoginToken
    @PostMapping("refunds/cancelRefunds")
    public Result<Object> cancelRefunds(@RequestParam String orderId,
                                        @RequestParam Integer refundsType) {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        int rowA = refundsService.delRefunds(account, orderId);
        int rowB = orderService.putOrderStatus(account, orderId, refundsType == 0 ? 1 : 2);
        if (rowA == 0 || rowB == 0) {
            return Result.error(CodeMsg.FAIL, "取消失败");
        }
        return Result.success(CodeMsg.SUCCESS, "取消成功");
    }
}
