package shop.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.entity.CodeMsg;
import shop.demo.entity.Collect;
import shop.demo.entity.Result;
import shop.demo.service.CollectService;
import shop.demo.utils.TokenUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CollectController {
    @Autowired
    private CollectService collectService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 添加收藏或删除商品
     *
     * @param goodsId * String 商品id
     */
    @PostMapping("goods/addOrDelCollectGoods")
    public Result<Object> addOrDelCollectGoods(@RequestParam String goodsId) {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        if (goodsId == null) {
            return Result.error(CodeMsg.PARAMETER_ISNULL);
        }
        Collect goods = collectService.hasCollectGoods(account, goodsId);
        int row = 0;
        Boolean isCollect = goods != null;
        if (isCollect) { //删除
            row = collectService.delCollectGoods(account, goodsId);
        } else { //添加
            row = collectService.addCollectGoods(account, goodsId);
        }
        if (row == 0) {
            return Result.error(CodeMsg.FAIL, isCollect ? "商品取消收藏失败" : "商品收藏失败");
        }
        return Result.success(CodeMsg.SUCCESS, isCollect ? "商品取消收藏成功" : "商品收藏成功");
    }

    /**
     * 商品是否收藏
     *
     * @param goodsId * String 商品id
     */
    @PostMapping("goods/hasCollectGoods")
    public Result<Object> hasCollectGoods(@RequestParam String goodsId) {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        if (goodsId == null) {
            return Result.error(CodeMsg.PARAMETER_ISNULL);
        }
        Collect goods = collectService.hasCollectGoods(account, goodsId);
        if (goods == null) {
            return Result.error(CodeMsg.NOT_FIND_DATA);
        }
        return Result.success(goods.getGoodsId());
    }
}
