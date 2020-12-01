package shop.demo.entity;

import java.math.BigDecimal;
import java.util.Objects;

/*购物车*/
public class Cart extends Common {
	private Integer cartId;
    private Integer goodsSpecsId;
    private String goodsId;
    private Integer number;
    private BigDecimal price;
    private String skuDesc;
    private String skuCover;

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSkuDesc() {
        return skuDesc;
    }

    public void setSkuDesc(String skuDesc) {
        this.skuDesc = skuDesc;
    }

    public String getSkuCover() {
        return skuCover;
    }

    public void setSkuCover(String skuCover) {
        this.skuCover = skuCover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cart cart = (Cart) o;
        return Objects.equals(cartId, cart.cartId) &&
                Objects.equals(goodsSpecsId, cart.goodsSpecsId) &&
                Objects.equals(goodsId, cart.goodsId) &&
                Objects.equals(number, cart.number) &&
                Objects.equals(price, cart.price) &&
                Objects.equals(skuDesc, cart.skuDesc) &&
                Objects.equals(skuCover, cart.skuCover);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cartId, goodsSpecsId, goodsId, number, price, skuDesc, skuCover);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", goodsSpecsId=" + goodsSpecsId +
                ", goodsId='" + goodsId + '\'' +
                ", number=" + number +
                ", price=" + price +
                ", skuDesc='" + skuDesc + '\'' +
                ", skuCover='" + skuCover + '\'' +
                '}';
    }
}
