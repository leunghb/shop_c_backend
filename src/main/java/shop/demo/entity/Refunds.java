package shop.demo.entity;

import java.sql.Timestamp;
import java.util.Objects;

/*退货/退款记录*/
public class Refunds extends Common {
    private String orderId;
    private Integer type;
    private Timestamp applyTime;
    private Timestamp confirmTime;
    private String cause;
    private String description;
    private String image;
    private Integer agreeOrNot;
    private String sallerDescription;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Timestamp getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Timestamp applyTime) {
        this.applyTime = applyTime;
    }

    public Timestamp getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Timestamp confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getAgreeOrNot() {
        return agreeOrNot;
    }

    public void setAgreeOrNot(Integer agreeOrNot) {
        this.agreeOrNot = agreeOrNot;
    }

    public String getSallerDescription() {
        return sallerDescription;
    }

    public void setSallerDescription(String sallerDescription) {
        this.sallerDescription = sallerDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Refunds refunds = (Refunds) o;
        return Objects.equals(orderId, refunds.orderId) &&
                Objects.equals(type, refunds.type) &&
                Objects.equals(applyTime, refunds.applyTime) &&
                Objects.equals(confirmTime, refunds.confirmTime) &&
                Objects.equals(cause, refunds.cause) &&
                Objects.equals(description, refunds.description) &&
                Objects.equals(image, refunds.image) &&
                Objects.equals(agreeOrNot, refunds.agreeOrNot) &&
                Objects.equals(sallerDescription, refunds.sallerDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderId, type, applyTime, confirmTime, cause, description, image, agreeOrNot, sallerDescription);
    }

    @Override
    public String toString() {
        return "Refunds{" +
                "orderId='" + orderId + '\'' +
                ", type=" + type +
                ", applyTime=" + applyTime +
                ", confirmTime=" + confirmTime +
                ", cause='" + cause + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", agreeOrNot=" + agreeOrNot +
                ", sallerDescription='" + sallerDescription + '\'' +
                '}';
    }
}
