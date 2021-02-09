package shop.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.demo.entity.AttrValue;
import shop.demo.entity.GoodsSpecs;
import shop.demo.mapper.GoodsSpecsMapper;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class GoodsSpecsService {
    @Autowired
    private GoodsSpecsMapper goodsSpecsMapper;

    public List<GoodsSpecs> getGoodsSpecs(String goodsId) {
        return goodsSpecsMapper.getGoodsSpecsByGoodsId(goodsId);
    }

    public int putGoodsSpecsStock(int id, int stock) {
        return goodsSpecsMapper.putGoodsSpecsStock(id, stock);
    }

    public List<Map<String, Object>> getAttrKey(int goodsTypeId) {
        return goodsSpecsMapper.getAttrKey(goodsTypeId);
    }

    public List<AttrValue> getAttrValue(int id) {
        return goodsSpecsMapper.getAttrValue(id);
    }

    public int addAttrKey(int goodsTypeId, String name) {
        return goodsSpecsMapper.addAttrKey(goodsTypeId, name);
    }

    public int addAttrValue(int attrKeyId, String name) {
        return goodsSpecsMapper.addAttrValue(attrKeyId, name);
    }

    public int delAttrValue(int id) {
        return goodsSpecsMapper.delAttrValue(id);
    }
}
