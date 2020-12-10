package shop.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.demo.mapper.RefundsMapper;

import java.sql.Timestamp;

@Transactional
@Service
public class RefundsService {
    @Autowired
    private RefundsMapper refundsMapper;

    public int addRefunds(String orderId, String account, Integer type, Timestamp applyTime, String cause, String description,
                          String image) {
        return refundsMapper.addRefunds(orderId, account, type, applyTime, cause, description, image);
    }
}
