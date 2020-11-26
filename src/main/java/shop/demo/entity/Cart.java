package shop.demo.entity;

import java.util.Objects;

/*购物车*/
public class Cart extends Common {
    private Integer goodsSpecsId;
    private String goodsId;
    private Integer number;

    public Integer getGoodsSpecsId() {
        return goodsSpecsId;
    }

    public void setGoodsSpecsId(Integer goodsSpecsId) {
        this.goodsSpecsId = goodsSpecsId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "goodsSpecsId=" + goodsSpecsId +
                ", goodsId='" + goodsId + '\'' +
                ", number=" + number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(goodsSpecsId, cart.goodsSpecsId) &&
                Objects.equals(goodsId, cart.goodsId) &&
                Objects.equals(number, cart.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), goodsSpecsId, goodsId, number);
    }
}
