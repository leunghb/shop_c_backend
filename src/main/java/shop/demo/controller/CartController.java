package shop.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.entity.Cart;
import shop.demo.entity.CodeMsg;
import shop.demo.entity.Result;
import shop.demo.service.CartService;
import shop.demo.utils.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 添加商品到购物车
     *
     * @param goodsSpecsId * int 商品规格单品id（goods_specs表id）
     * @param goodsId      * String 商品id
     * @param number       * int 购买数目
     * @param skuDesc      String 规格描述
     * @param skuCover     String 规格单品图片
     */
    @PostMapping("cart/addGoodsToCart")
    public Result<Object> addGoodsToCart(@RequestParam Integer goodsSpecsId, @RequestParam String goodsId,
                                         @RequestParam Integer number,
                                         @RequestParam(required = false) String skuDesc, @RequestParam(required =
            false) String skuCover) {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        if (goodsSpecsId == null || goodsId == "" || number == null) {
            return Result.error(CodeMsg.PARAMETER_ISNULL);
        }

        Cart goods = cartService.getCartOneGoods(account, goodsSpecsId, goodsId);

        int row = 0;
        if (goods == null) { //添加
            row = cartService.addGoodsToCart(account, goodsSpecsId, goodsId, number, skuDesc, skuCover);
        } else { //更新
            number += goods.getNumber();
            row = cartService.putCartOneGoods(account, goodsSpecsId, goodsId, number);
        }

        if (row == 0) {
            return Result.error(CodeMsg.FAIL, "添加到购物车失败");
        }
        return Result.success(CodeMsg.SUCCESS, "添加到购物车成功");
    }

    /**
     * 获取购物车列表
     *
     * @param limit int 每页数目
     * @param page  int 页数
     */
    @PostMapping("cart/getCartList")
    public Result<Object> getCartList(@RequestParam(required = false, defaultValue = "10") int limit,
                                      @RequestParam(required = false, defaultValue = "1") int page) {
        page = (page - 1) * limit;
        String account = TokenUtil.getJwtToken(httpServletRequest);
        List<Object> list = (List<Object>) cartService.getCartList(account, limit, page);
        return Result.success(list);
    }
    
    /**
     * 删除购物车商品
     * 
     * @param cartId * int 购物车cartId
     */
    @PostMapping("cart/delCartOneGoods")
    public Result<Object> delCartOneGoods(@RequestParam Integer cartId) {
    	String account = TokenUtil.getJwtToken(httpServletRequest);
    	if(cartId == null) {
    		return Result.error(CodeMsg.PARAMETER_ISNULL);
    	}
    	int row = cartService.delCartOneGoods(account, cartId);
    	
    	if(row == 0) {
    		return Result.error(CodeMsg.FAIL, "删除失败");
    	}
    	
    	return Result.success(CodeMsg.SUCCESS,"删除成功");
    }
}
