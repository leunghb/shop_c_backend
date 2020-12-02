package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.demo.entity.Order;

import java.math.BigDecimal;

@Mapper
public interface OrderMapper {
    int addOrder(String orderId, String account, Integer addressId, BigDecimal totalPrice, String info, Integer paymentLimitTime);

    Order getOrder(String account, String orderId);
}
