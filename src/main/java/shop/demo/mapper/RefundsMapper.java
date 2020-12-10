package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.sql.Time;
import java.sql.Timestamp;

@Mapper
public interface RefundsMapper {
    int addRefunds(String orderId, String account, Integer type, Timestamp applyTime, String cause, String description,
                   String image);
}
