package shop.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.demo.entity.Cart;
import shop.demo.mapper.CartMapper;

import java.util.List;

@Transactional
@Service
public class CartService {
    @Autowired
    private CartMapper cartMapper;

    public int addGoodsToCart(String account, int goodsSpecsId, String goodsId, int number, String skuDesc, String skuCover) {
        return cartMapper.addGoodsToCart(account, goodsSpecsId, goodsId, number, skuDesc, skuCover);
    }

    public Cart getCartOneGoods(String account, int goodsSpecsId, String goodsId) {
        return cartMapper.getCartOneGoods(account, goodsSpecsId, goodsId);
    }

    public int putCartOneGoods(String account, int goodsSpecsId, String goodsId, int number) {
        return cartMapper.putCartOneGoods(account, goodsSpecsId, goodsId, number);
    }

    public List<Object> getCartList(String account, int limit, int page) {
        return cartMapper.getCartList(account, limit, page);
    }
    
    public int delCartOneGoods(String account, int cartId) {
    	return cartMapper.delCartOneGoods(account, cartId);
    }
}
