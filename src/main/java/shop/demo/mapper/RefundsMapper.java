package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.demo.entity.Refunds;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Mapper
public interface RefundsMapper {
    int addRefunds(String orderId, String account, Integer type, String cause, String description,
                   String image);

    Refunds getRefunds(String account, String orderId);

    int delRefunds(String account, String orderId);
}
