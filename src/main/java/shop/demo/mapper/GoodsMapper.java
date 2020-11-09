package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import shop.demo.entity.Goods;
import shop.demo.entity.GoodsType;

import java.util.List;

@Mapper
public interface GoodsMapper {
    List<GoodsType> getGoodsType(int soldOut);

    List<Goods> getGoods(int soldOut, int goodsTypeId, String mainTitle);
}
