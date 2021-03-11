package shop.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.demo.entity.User;
import shop.demo.entity.UserBalanceRecord;
import shop.demo.mapper.UserMapper;

import java.math.BigDecimal;
import java.util.List;

@Transactional
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

    public User getUserByAccount(String account) {
        return userMapper.getUserByAccount(account);
    }

    public int addUser(String account, String password) {
        return userMapper.addUser(account, password);
    }

    public User getUserByAccountAndPassword(String account, String password) {
        return userMapper.getUserByAccountAndPassword(account, password);
    }

    public int putUserPwdByAccount(String account, String password) {
        return userMapper.putUserPwdByAccount(account, password);
    }

    public List<UserBalanceRecord> getUserBalanceRecord(String account) {
        return userMapper.getUserBalanceRecord(account);
    }

    public BigDecimal getUserBalance(String account) {
        return userMapper.getUserBalance(account);
    }

    public int putUserBalance(String account, BigDecimal balance, int type) {
        return userMapper.putUserBalance(account, balance, type);
    }

    public int putUserAvatarOrName(String account, String avatar, String name) {
        return userMapper.putUserAvatarOrName(account, avatar, name);
    }
}
