package shop.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.demo.entity.Order;
import shop.demo.mapper.OrderMapper;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public int addOrder(String orderId, String account, Integer addressId, BigDecimal totalPrice,
                        String info, Integer paymentLimitTime) {
        return orderMapper.addOrder(orderId, account, addressId, totalPrice, info, paymentLimitTime);
    }

    public Order getOrder(String account, String orderId) {
        return orderMapper.getOrder(account, orderId);
    }

    public int putOrderStatus(String account, String orderId, Integer orderStatus) {
        System.out.println(account);
        System.out.println(orderId);
        System.out.println(orderStatus);
        return orderMapper.putOrderStatus(account, orderId, orderStatus);
    }

    public int putOrderAddress(String account, String orderId, Integer addressId) {
        return orderMapper.putOrderAddress(account, orderId, addressId);
    }

    public List<Order> getOrderList(String account, Integer orderStatus, Integer limit, Integer page) {
        return orderMapper.getOrderList(account, orderStatus, limit, page);
    }

    public int getOrderCount(int orderStatus) {
        return orderMapper.getOrderCount(orderStatus);
    }
}
