package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.demo.entity.AttrKey;
import shop.demo.entity.AttrValue;
import shop.demo.entity.GoodsSpecs;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsSpecsMapper {
    List<GoodsSpecs> getGoodsSpecsByGoodsId(String goodsId);

    int putGoodsSpecsStock(int id, int stock);

    List<Map<String, Object>> getAttrKey(int goodsTypeId);

    List<AttrValue> getAttrValue(int id);

    int addAttrKey(int goodsTypeId, String name);

    void addAttrValue(AttrValue attrValue);

    int delAttrValue(int id);

    int delAttrKey(int id);

    List<AttrKey> getAttrKeyByGoodsType(int goodsTypeId);

    List<AttrValue> getAttrValueByKeyId(int attrKeyId);

    void delGoodsSpecs(String goodsId);

    void addGoodsSpecs(String goodsId, String specs, int stock, BigDecimal price);
}
