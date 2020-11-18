package shop.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.demo.entity.GoodsSpecs;
import shop.demo.mapper.GoodsSpecsMapper;

import java.util.List;

@Transactional
@Service
public class GoodsSpecsService {
    @Autowired
    private GoodsSpecsMapper goodsSpecsMapper;

    public List<GoodsSpecs> getGoodsSpecs(String goodsId) {
        return goodsSpecsMapper.getGoodsSpecsByGoodsId(goodsId);
    }
}
