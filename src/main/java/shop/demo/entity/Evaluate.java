package shop.demo.entity;

import java.util.Objects;

/*评价*/
public class Evaluate extends Common {
    private String orderId;
    private String goodsId;
    private Integer goodsSpecsId;
    private Integer rate;
    private String comment;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getGoodsSpecsId() {
        return goodsSpecsId;
    }

    public void setGoodsSpecsId(Integer goodsSpecsId) {
        this.goodsSpecsId = goodsSpecsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Evaluate evaluate = (Evaluate) o;
        return Objects.equals(orderId, evaluate.orderId) &&
                Objects.equals(goodsId, evaluate.goodsId) &&
                Objects.equals(goodsSpecsId, evaluate.goodsSpecsId) &&
                Objects.equals(rate, evaluate.rate) &&
                Objects.equals(comment, evaluate.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderId, goodsId, goodsSpecsId, rate, comment);
    }

    @Override
    public String toString() {
        return "Evaluate{" +
                "orderId='" + orderId + '\'' +
                ", goodsId='" + goodsId + '\'' +
                ", goodsSpecsId=" + goodsSpecsId +
                ", rate=" + rate +
                ", comment='" + comment + '\'' +
                '}';
    }
}
