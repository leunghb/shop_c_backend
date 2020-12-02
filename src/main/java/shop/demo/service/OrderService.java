package shop.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.demo.entity.Order;
import shop.demo.mapper.OrderMapper;

import java.math.BigDecimal;

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
}
