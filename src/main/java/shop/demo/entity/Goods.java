package shop.demo.entity;

import java.math.BigDecimal;
import java.util.Objects;

/*商品*/
public class Goods extends Common {
    private String goodsId;
    private BigDecimal goodsTypeId;
    private BigDecimal originalPrice;
    private int discountPrice;
    private String cover;
    private String mainTitle;
    private String subTitle;
    private String content;
    private int salesVolume;
    private int soldOut;
    private int stock;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(BigDecimal goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
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

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    public int getSoldOut() {
        return soldOut;
    }

    public void setSoldOut(int soldOut) {
        this.soldOut = soldOut;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId='" + goodsId + '\'' +
                ", goodsTypeId='" + goodsTypeId + '\'' +
                ", originalPrice=" + originalPrice +
                ", discountPrice=" + discountPrice +
                ", cover='" + cover + '\'' +
                ", mainTitle='" + mainTitle + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", content='" + content + '\'' +
                ", salesVolume=" + salesVolume +
                ", soldOut=" + soldOut +
                ", stock=" + stock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Goods goods = (Goods) o;
        return originalPrice == goods.originalPrice &&
                discountPrice == goods.discountPrice &&
                salesVolume == goods.salesVolume &&
                soldOut == goods.soldOut &&
                stock == goods.stock &&
                Objects.equals(goodsId, goods.goodsId) &&
                Objects.equals(goodsTypeId, goods.goodsTypeId) &&
                Objects.equals(cover, goods.cover) &&
                Objects.equals(mainTitle, goods.mainTitle) &&
                Objects.equals(subTitle, goods.subTitle) &&
                Objects.equals(content, goods.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), goodsId, goodsTypeId, originalPrice, discountPrice, cover, mainTitle, subTitle, content, salesVolume, soldOut, stock);
    }
}
