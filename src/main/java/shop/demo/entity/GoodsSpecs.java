package shop.demo.entity;

import java.math.BigDecimal;
import java.util.Objects;

/*商品规格*/
public class GoodsSpecs extends Common {
    private String goodsId;
    private String specs;
    private Integer stock;
    private BigDecimal price;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GoodsSpecs that = (GoodsSpecs) o;
        return Objects.equals(goodsId, that.goodsId) &&
                Objects.equals(specs, that.specs) &&
                Objects.equals(stock, that.stock) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), goodsId, specs, stock, price);
    }

    @Override
    public String toString() {
        return "GoodsSpecs{" +
                "goodsId='" + goodsId + '\'' +
                ", specs='" + specs + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                '}';
    }
}
