package shop.demo.entity;

import java.util.Objects;

/*商品分类*/
public class GoodsType extends Common {
    private String title;
    private Integer soldOut;
    private Integer priority;
    private String icon;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSoldOut() {
        return soldOut;
    }

    public void setSoldOut(Integer soldOut) {
        this.soldOut = soldOut;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GoodsType goodsType = (GoodsType) o;
        return soldOut == goodsType.soldOut &&
                priority == goodsType.priority &&
                Objects.equals(title, goodsType.title) &&
                Objects.equals(icon, goodsType.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, soldOut, priority, icon);
    }

    @Override
    public String toString() {
        return "GoodsType{" +
                "title='" + title + '\'' +
                ", soldOut=" + soldOut +
                ", priority=" + priority +
                ", icon='" + icon + '\'' +
                '}';
    }
}
