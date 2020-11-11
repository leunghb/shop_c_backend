package shop.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Objects;

/*商品*/
public class Goods extends Common {
    private String goodsId;
    private BigDecimal goodsTypeId;
    private BigDecimal originalPrice;
    private Integer discountPrice;
    private String cover;
    private String mainTitle;
    private String subTitle;
    private String content;
    private Integer salesVolume;
    private Integer soldOut;
    private Integer stock;

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

    public Integer getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Integer discountPrice) {
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

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Integer getSoldOut() {
        return soldOut;
    }

    public void setSoldOut(Integer soldOut) {
        this.soldOut = soldOut;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
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
