package shop.demo.entity;

import java.util.Objects;

/*规格组*/
public class attrKey extends Common {
    private Integer goodsTypeId;
    private String name;

    public Integer getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Integer goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        attrKey attrKey = (attrKey) o;
        return Objects.equals(goodsTypeId, attrKey.goodsTypeId) &&
                Objects.equals(name, attrKey.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), goodsTypeId, name);
    }

    @Override
    public String toString() {
        return "attrKey{" +
                "goodsTypeId=" + goodsTypeId +
                ", name='" + name + '\'' +
                '}';
    }
}
