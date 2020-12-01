package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.demo.entity.Collect;

@Mapper
public interface CollectMapper {
    int addCollectGoods(String account, String goodsId);

    Collect hasCollectGoods(String account, String goodsId);

    int delCollectGoods(String account, String goodsId);
}
