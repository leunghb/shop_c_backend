package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.demo.entity.Cart;

import java.util.List;

@Mapper
public interface CartMapper {
    int addGoodsToCart(String account, int goodsSpecsId, String goodsId, int number, String skuDesc, String skuCover);

    Cart getCartOneGoods(String account, int goodsSpecsId, String goodsId);

    int putCartOneGoods(String account, int goodsSpecsId, String goodsId, int number);

    List<Object> getCartList(String account, int limit, int page);
    
    int delCartOneGoods(String account, int cartId);
}
