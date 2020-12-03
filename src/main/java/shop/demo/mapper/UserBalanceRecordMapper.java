package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

@Mapper
public interface UserBalanceRecordMapper {
    int addUserBalanceRecord(String account, int useType, BigDecimal useAmount, String orderId);
}
