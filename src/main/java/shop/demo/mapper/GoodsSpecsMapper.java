package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.demo.entity.GoodsSpecs;

import java.util.List;

@Mapper
public interface GoodsSpecsMapper {
    List<GoodsSpecs> getGoodsSpecsByGoodsId(String goodsId);
}
