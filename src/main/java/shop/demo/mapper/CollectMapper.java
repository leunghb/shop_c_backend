package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.demo.entity.Collect;

import java.util.List;

@Mapper
public interface CollectMapper {
    int addCollectGoods(String account, String goodsId);

    Collect hasCollectGoods(String account, String goodsId);

    int delCollectGoods(String account, String goodsId);

    List<Object> getUserGoodsCollectList(String account);
}
