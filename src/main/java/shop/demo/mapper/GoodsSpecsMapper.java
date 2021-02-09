package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.demo.entity.AttrKey;
import shop.demo.entity.AttrValue;
import shop.demo.entity.GoodsSpecs;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsSpecsMapper {
    List<GoodsSpecs> getGoodsSpecsByGoodsId(String goodsId);

    int putGoodsSpecsStock(int id, int stock);

    List<Map<String, Object>> getAttrKey(int goodsTypeId);

    List<AttrValue> getAttrValue(int id);

    int addAttrKey(int goodsTypeId, String name);

    int addAttrValue(int attrKeyId, String name);

    int delAttrValue(int id);
}
