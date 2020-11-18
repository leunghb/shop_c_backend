package shop.demo.entity;

import java.util.Objects;

/*规格值*/
public class attrValue extends Common {
    private Integer attrKeyId;
    private String name;

    public Integer getAttrKeyId() {
        return attrKeyId;
    }

    public void setAttrKeyId(Integer attrKeyId) {
        this.attrKeyId = attrKeyId;
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
        attrValue attrValue = (attrValue) o;
        return Objects.equals(attrKeyId, attrValue.attrKeyId) &&
                Objects.equals(name, attrValue.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), attrKeyId, name);
    }

    @Override
    public String toString() {
        return "attrValue{" +
                "attrKeyId=" + attrKeyId +
                ", name='" + name + '\'' +
                '}';
    }
}
