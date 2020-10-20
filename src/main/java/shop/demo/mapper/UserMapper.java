package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.demo.entity.User;
import shop.demo.entity.VerifyCode;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAllUser();

    User getUserByAccount(String account);

    int addUser(String account, String password);

    int addVerifyCode(String account, String code, int effectiveTime);

    int putVerifyCode(String account, String code, int effectiveTime);

    VerifyCode getVerifyCodeByAccount(String account);
}
