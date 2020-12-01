package shop.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.demo.entity.Collect;
import shop.demo.mapper.CollectMapper;

@Transactional
@Service
public class CollectService {
    @Autowired
    private CollectMapper collectMapper;

    public int addCollectGoods(String account, String goodsId) {
        return collectMapper.addCollectGoods(account, goodsId);
    }

    public Collect hasCollectGoods(String account, String goodsId) {
        return collectMapper.hasCollectGoods(account, goodsId);
    }

    public int delCollectGoods(String account, String goodsId) {
        return collectMapper.delCollectGoods(account, goodsId);
    }
}
