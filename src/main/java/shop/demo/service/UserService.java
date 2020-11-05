package shop.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.demo.entity.User;
import shop.demo.mapper.UserMapper;

import java.util.List;

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
}
