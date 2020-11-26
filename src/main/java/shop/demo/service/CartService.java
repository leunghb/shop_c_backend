package shop.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.demo.entity.Cart;
import shop.demo.mapper.CartMapper;

@Transactional
@Service
public class CartService {
    @Autowired
    private CartMapper cartMapper;

    public int addGoodsToCart(String account, int goodsSpecsId, String goodsId, int number) {
        return cartMapper.addGoodsToCart(account, goodsSpecsId, goodsId, number);
    }

    public Cart getCartOneGoods(String account, int goodsSpecsId, String goodsId) {
        return cartMapper.getCartOneGoods(account, goodsSpecsId, goodsId);
    }

    public int putCartOneGoods(String account, int goodsSpecsId, String goodsId, int number) {
        return cartMapper.putCartOneGoods(account, goodsSpecsId, goodsId, number);
    }
}
