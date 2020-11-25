package shop.demo.entity;

import java.util.Objects;

public class Address extends Common {
    private String name;
    private String tel;
    private String address;
    private Integer isDefault;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Address address1 = (Address) o;
        return Objects.equals(name, address1.name) &&
                Objects.equals(tel, address1.tel) &&
                Objects.equals(address, address1.address) &&
                Objects.equals(isDefault, address1.isDefault);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, tel, address, isDefault);
    }

    @Override
    public String toString() {
        return "Address{" +
                "name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }
}
