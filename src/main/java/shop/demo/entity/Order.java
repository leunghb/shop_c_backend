package shop.demo.entity;

import java.math.BigDecimal;
import java.util.Objects;

/*订单*/
public class Order extends Common {
    private String orderId;
    private Integer addressId;
    private Integer orderStatus;
    private BigDecimal totalPrice;
    private String cartId;

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

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
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
                Objects.equals(cartId, order.cartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderId, addressId, orderStatus, totalPrice, cartId);
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", addressId=" + addressId +
                ", orderStatus=" + orderStatus +
                ", totalPrice=" + totalPrice +
                ", cartId=" + cartId +
                '}';
    }
}
