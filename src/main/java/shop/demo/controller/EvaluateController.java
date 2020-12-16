package shop.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.config.UserLoginToken;
import shop.demo.entity.CodeMsg;
import shop.demo.entity.Evaluate;
import shop.demo.entity.Result;
import shop.demo.service.EvaluateService;
import shop.demo.utils.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class EvaluateController {
    @Autowired
    private EvaluateService evaluateService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 添加评价
     *
     * @param orderId      * string
     * @param goodsId      * string
     * @param goodsSpecsId * int
     * @param rate         * int
     * @param comment      string
     */
    @UserLoginToken
    @PostMapping("order/addEvaluate")
    public Result<Object> addEvaluate(@RequestParam String orderId,
                                      @RequestParam String goodsId,
                                      @RequestParam Integer goodsSpecsId,
                                      @RequestParam Integer rate,
                                      @RequestParam(required = false, defaultValue = "") String comment) {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        int row = evaluateService.addEvaluate(account, orderId, goodsId, goodsSpecsId, rate, comment);
        if (row == 0) {
            return Result.error(CodeMsg.FAIL);
        }
        return Result.success(CodeMsg.SUCCESS);
    }

    /**
     * 获取订单商品的评论
     *
     * @param orderId      * string
     * @param goodsId
     * @param goodsSpecsId
     */
    @UserLoginToken
    @PostMapping("order/getEvaluate")
    public Result<Object> selEvaluate(@RequestParam String orderId,
                                      @RequestParam(required = false, defaultValue = "") String goodsId,
                                      @RequestParam Integer goodsSpecsId) {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        List<Evaluate> list = evaluateService.selEvaluate(account, orderId, goodsSpecsId, goodsId);
        if (list.size() == 0) {
            return Result.success(CodeMsg.NOT_FIND_DATA);
        }
        return Result.success(list);
    }

    /**
     * 获取商品的评论
     *
     * @param goodsId * string
     * @param type int (0-差评：0<=type<3；1-中评：3<=type<5；2-好评：type=5)
     */
    @PostMapping("goods/getGoodsEvaluateList")
    public Result<Object> selGoodsEvaluateList(@RequestParam String goodsId,
                                               @RequestParam(required = false, defaultValue = "") Integer type) {
        List<Object> list = evaluateService.selGoodsEvaluateList(goodsId, type);
        if (list.size() == 0) {
            return Result.success(CodeMsg.NOT_FIND_DATA);
        }
        return Result.success(list);
    }
}
