package shop.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.demo.entity.Refunds;
import shop.demo.mapper.RefundsMapper;

import java.sql.Date;
import java.sql.Timestamp;

@Transactional
@Service
public class RefundsService {
    @Autowired
    private RefundsMapper refundsMapper;

    public int addRefunds(String orderId, String account, Integer type, String cause, String description,
                          String image) {
        return refundsMapper.addRefunds(orderId, account, type, cause, description, image);
    }

    public Refunds getRefunds(String account, String orderId) {
        return refundsMapper.getRefunds(account, orderId);
    }

    public int delRefunds(String account, String orderId) {
        return refundsMapper.delRefunds(account, orderId);
    }
}
