package shop.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import shop.demo.entity.Goods;
import shop.demo.entity.GoodsType;
import shop.demo.mapper.GoodsMapper;

import java.util.List;

@Transactional
@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    public List<GoodsType> getGoodsType(int soldOut) {
        return goodsMapper.getGoodsType(soldOut);
    }

    public List<Goods> getGoods(int soldOut, int goodsTypeId, String mainTitle, int limit, int page) {
        return goodsMapper.getGoods(soldOut, goodsTypeId, mainTitle, limit, page);
    }

    public List<Goods> getHotGoods() {
        return goodsMapper.getHotGoods();
    }
}
