package shop.demo.entity;

import java.util.Objects;

public class Goods extends Common {
    private String goodsId;
    private int originalPrice;
    private int discountPrice;
    private String cover;
    private String mainTitle;
    private String subTitle;
    private String content;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Goods goods = (Goods) o;
        return originalPrice == goods.originalPrice &&
                discountPrice == goods.discountPrice &&
                Objects.equals(goodsId, goods.goodsId) &&
                Objects.equals(cover, goods.cover) &&
                Objects.equals(mainTitle, goods.mainTitle) &&
                Objects.equals(subTitle, goods.subTitle) &&
                Objects.equals(content, goods.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), goodsId, originalPrice, discountPrice, cover, mainTitle, subTitle, content);
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId='" + goodsId + '\'' +
                ", originalPrice=" + originalPrice +
                ", discountPrice=" + discountPrice +
                ", cover='" + cover + '\'' +
                ", mainTitle='" + mainTitle + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
