package shop.demo.entity;

import java.util.Objects;

/*商品收藏*/
public class Collect extends Common {
    private String goodsId;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Collect collect = (Collect) o;
        return Objects.equals(goodsId, collect.goodsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), goodsId);
    }

    @Override
    public String toString() {
        return "Collect{" +
                "goodsId='" + goodsId + '\'' +
                '}';
    }
}
