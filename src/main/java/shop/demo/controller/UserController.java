package shop.demo.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sun.xml.internal.ws.developer.Serialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.entity.Result;
import shop.demo.entity.User;
import shop.demo.service.UserService;

import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /*获取所有用户 账号、创建时间*/
    @GetMapping("/getAllUser")
    public Result<Object> getAllUser() {
        List<User> list = userService.getAllUser();
        for (User user : list) {
            System.out.println(user);
            User user1 = new User();
            user1.setAccount(user.getAccount());
            user1.setCreatedAt(user.getCreatedAt());
        }
        return Result.success(user1);
    }
}
