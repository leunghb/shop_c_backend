package shop.demo.entity;

import java.math.BigDecimal;
import java.util.Objects;

/*订单*/
public class Order extends Common {
    private String orderId;
    private Integer addressId;
    private Integer orderStatus;
    private BigDecimal totalPrice;
    private String info;
    private Integer paymentLimitTime;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getPaymentLimitTime() {
        return paymentLimitTime;
    }

    public void setPaymentLimitTime(Integer paymentLimitTime) {
        this.paymentLimitTime = paymentLimitTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(orderId, order.orderId) &&
                Objects.equals(addressId, order.addressId) &&
                Objects.equals(orderStatus, order.orderStatus) &&
                Objects.equals(totalPrice, order.totalPrice) &&
                Objects.equals(info, order.info) &&
                Objects.equals(paymentLimitTime, order.paymentLimitTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderId, addressId, orderStatus, totalPrice, info, paymentLimitTime);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", addressId=" + addressId +
                ", orderStatus=" + orderStatus +
                ", totalPrice=" + totalPrice +
                ", info='" + info + '\'' +
                ", paymentLimitTime=" + paymentLimitTime +
                '}';
    }
}
