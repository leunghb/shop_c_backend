package shop.demo.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class VerifyCode {
    private Long id;
    private String account;
    private String code;
    private int effectiveTime;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(int effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        VerifyCode that = (VerifyCode) o;
        return effectiveTime == that.effectiveTime && Objects.equals(id, that.id)
                && Objects.equals(account, that.account) && Objects.equals(code, that.code)
                && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, code, effectiveTime, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "VerifyCode{" + "id=" + id + ", account='" + account + '\'' + ", code='" + code + '\''
                + ", effectiveTime=" + effectiveTime + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}
