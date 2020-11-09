package shop.demo.entity;

import java.util.Objects;

/*商品分类*/
public class GoodsType extends Common {
    private String title;
    private int soldOut;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSoldOut() {
        return soldOut;
    }

    public void setSoldOut(int soldOut) {
        this.soldOut = soldOut;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GoodsType goodsType = (GoodsType) o;
        return soldOut == goodsType.soldOut &&
                Objects.equals(title, goodsType.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, soldOut);
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "title='" + title + '\'' +
                ", soldOut=" + soldOut +
                '}';
    }
}
