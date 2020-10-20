package shop.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.entity.Result;
import shop.demo.entity.User;
import shop.demo.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取所有用户
     *
     * @return 账号 创建时间
     */
    @GetMapping("/getAllUser")
    public Result<Object> getAllUser() {
        List<User> users = userService.getAllUser();
        return Result.success(users);
    }

    /**
     * 获取单个用户
     *
     * @param account string 账号
     */
    @PostMapping("getUserByAccount")
    public Result<User> getUserByAccount(@RequestParam Map<String, Object> map) {
        String account = map.get("account").toString();
        User user = userService.getUserByAccount(account);
        return Result.success(user);
    }

    /**
     * 注册
     * @param account string 账号
     * @param password string 密码
     * @param code string 验证码
     * @param effectiveTime int 有效时间（分钟）
     */
//    @PostMapping("/register")
}
