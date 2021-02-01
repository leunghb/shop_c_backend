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

    public int delGoodsType(int id) {
        return goodsMapper.delGoodsType(id);
    }

    public List<Goods> getGoodsList(int soldOut, int goodsTypeId, String mainTitle, int limit, int page) {
        return goodsMapper.getGoodsList(soldOut, goodsTypeId, mainTitle, limit, page);
    }

    public int getCountWhereSomething(int soldOut, int goodsTypeId, String mainTitle, int limit, int page) {
        return goodsMapper.getCountWhereSomething(soldOut, goodsTypeId, mainTitle, limit, page);
    }

    public List<Goods> getHotGoods(int num) {
        return goodsMapper.getHotGoods(num);
    }

    public Goods getGoodsDetail(String goodsId) {
        return goodsMapper.getGoodsDetail(goodsId);
    }

    public int putGoodsStock(String goodsId, int stock) {
        return goodsMapper.putGoodsStock(goodsId, stock);
    }

    public int putGoodsSalesVolume(String goodsId, int number) {
        return goodsMapper.putGoodsSalesVolume(goodsId, number);
    }

    public int hasGoodsOfType(int id) {
        return goodsMapper.hasGoodsOfType(id);
    }

    public int addGoodsType(String title, int soldOut, int priority, String icon) {
        return goodsMapper.addGoodsType(title, soldOut, priority, icon);
    }

    public int putGoodsType(int id, String title, int soldOut, int priority, String icon) {
        return  goodsMapper.putGoodsType(id, title, soldOut, priority, icon);
    }
}
