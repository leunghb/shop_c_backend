package shop.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.Objects;

/*用户余额使用记录*/
public class UserBalanceRecord extends Common {
    private Integer useType;
    private Integer useAmount;
    private String orderId;

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    public Integer getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(Integer useAmount) {
        this.useAmount = useAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserBalanceRecord that = (UserBalanceRecord) o;
        return useType == that.useType &&
                useAmount == that.useAmount &&
                orderId == that.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), useType, useAmount, orderId);
    }

    @Override
    public String toString() {
        return "UserBalanceRecord{" +
                "useType=" + useType +
                ", useAmount=" + useAmount +
                ", orderId=" + orderId +
                '}';
    }
}
