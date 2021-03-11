package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;
import shop.demo.entity.User;
import shop.demo.entity.UserBalanceRecord;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAllUser();

    User getUserByAccount(String account);

    int addUser(String account, String password);

    User getUserByAccountAndPassword(String account, String password);

    int putUserPwdByAccount(String account, String password);

    List<UserBalanceRecord> getUserBalanceRecord(String account);

    BigDecimal getUserBalance(String account);

    int putUserBalance(String account, BigDecimal balance, int type);

    int putUserAvatarOrName(String account, String avatar, String name);
}
