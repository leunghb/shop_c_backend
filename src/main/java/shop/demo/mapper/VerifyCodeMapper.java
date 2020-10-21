package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.demo.entity.VerifyCode;

@Mapper
public interface VerifyCodeMapper {

    int addVerifyCode(String account, String code, int effectiveTime);

    VerifyCode getVerifyCodeByAccount(String account);
}
