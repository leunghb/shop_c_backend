package shop.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.demo.entity.UserBalanceRecord;
import shop.demo.mapper.UserBalanceRecordMapper;

import java.math.BigDecimal;

@Transactional
@Service
public class UserBalanceRecordService {
    @Autowired
    private UserBalanceRecordMapper userBalanceRecordMapper;

    public int addUserBalanceRecord(String account, int useType, BigDecimal useAmount, String orderId) {
        return userBalanceRecordMapper.addUserBalanceRecord(account, useType, useAmount, orderId);
    }
}
