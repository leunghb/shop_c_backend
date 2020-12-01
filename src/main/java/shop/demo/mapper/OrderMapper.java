package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;

@Mapper
public interface OrderMapper {
    int addOrder(String orderId, String account, Integer addressId, BigDecimal totalPrice, String cartId);
}
