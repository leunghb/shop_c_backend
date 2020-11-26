package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.demo.entity.Cart;

@Mapper
public interface CartMapper {
    int addGoodsToCart(String account, int goodsSpecsId, String goodsId, int number);

    Cart getCartOneGoods(String account, int goodsSpecsId, String goodsId);

    int putCartOneGoods(String account, int goodsSpecsId, String goodsId, int number);
}
