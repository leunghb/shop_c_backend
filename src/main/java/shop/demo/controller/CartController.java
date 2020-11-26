package shop.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.entity.Cart;
import shop.demo.entity.CodeMsg;
import shop.demo.entity.Result;
import shop.demo.service.CartService;
import shop.demo.utils.TokenUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 添加商品到购物车
     *
     * @param account      * String
     * @param goodsSpecsId * int 商品规格单品id（goods_specs表id）
     * @param goodsId      * String 商品id
     * @param number       * int 购买数目
     */
    @PostMapping("goods/addGoodsToCart")
    public Result<Object> addGoodsToCart(@RequestParam Integer goodsSpecsId, @RequestParam String goodsId,
                                         @RequestParam Integer number) {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        if (goodsSpecsId == null || goodsId == "" || number == null) {
            return Result.error(CodeMsg.PARAMETER_ISNULL);
        }

        Cart goods = cartService.getCartOneGoods(account, goodsSpecsId, goodsId);

        int row = 0;
        if (goods == null) {
            row = cartService.addGoodsToCart(account, goodsSpecsId, goodsId, number);
        } else {
            number += goods.getNumber();
            row = cartService.putCartOneGoods(account, goodsSpecsId, goodsId, number);
        }

        if (row == 0) {
            return Result.error(CodeMsg.FAIL, "添加到购物车失败");
        }
        return Result.success(CodeMsg.SUCCESS, "添加到购物车成功");
    }
}
