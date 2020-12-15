package shop.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.demo.entity.Evaluate;
import shop.demo.mapper.EvaluateMapper;

import java.util.List;

@Transactional
@Service
public class EvaluateService {
    @Autowired
    private EvaluateMapper evaluateMapper;

    public int addEvaluate(String account, String orderId, String goodsId, Integer goodsSpecsId, Integer rate,
                           String comment) {
        return evaluateMapper.addEvaluate(account, orderId, goodsId, goodsSpecsId, rate, comment);
    }

    public List<Evaluate> selEvaluate(String account, String orderId, Integer goodsSpecsId, String goodsId) {
        return evaluateMapper.selEvaluate(account, orderId, goodsSpecsId, goodsId);
    }
}
