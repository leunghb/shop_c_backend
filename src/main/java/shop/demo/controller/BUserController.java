package shop.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.entity.CodeMsg;
import shop.demo.entity.Result;
import shop.demo.service.BUserService;
import shop.demo.utils.TokenUtil;

@RestController
public class BUserController {
    @Autowired
    private BUserService bUserService;

    @PostMapping("b/user/login")
    public Result<Object> bLogin(@RequestParam String account, @RequestParam String password) {
        System.out.println(account);
        int id = bUserService.bLogin(account, password);
        System.out.println(id);
        if (id > 0) {
            String token = TokenUtil.token(account, password);
            return Result.success(token);
        }
        return Result.error(CodeMsg.FAIL);
    }
}
