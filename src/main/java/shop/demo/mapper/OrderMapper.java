package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.demo.entity.Order;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface OrderMapper {
    int addOrder(String orderId, String account, Integer addressId, BigDecimal totalPrice, String info, Integer paymentLimitTime);

    Order getOrder(String account, String orderId);

    int putOrderStatus(String account, String orderId, Integer orderStatus);

    List<Order> getOrderList(String account, Integer orderStatus, Integer limit, Integer page);
}
