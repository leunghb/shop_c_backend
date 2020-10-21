package shop.demo.controller;

import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.entity.CodeMsg;
import shop.demo.entity.Result;
import shop.demo.entity.User;
import shop.demo.entity.VerifyCode;
import shop.demo.service.MailService;
import shop.demo.service.UserService;
import shop.demo.service.VerifyCodeService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private VerifyCodeService verifyCodeService;
    @Autowired
    private MailService mailService;

    /**
     * 获取所有用户
     *
     * @return 账号 创建时间
     */
    @GetMapping("user/getAllUser")
    public Result<Object> getAllUser() {
        List<User> users = userService.getAllUser();
        return Result.success(users);
    }

    /**
     * 获取单个用户
     *
     * @param account string 账号
     */
    @PostMapping("user/getUserByAccount")
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

    /**
     * 发送验证码
     *
     * @param account       string 账号
     * @param effectiveTime int 有效时间（分钟）
     */
    @PostMapping("user/sendVerifyCode")
    public Result<Object> sendVerifyCode(@RequestParam Map<String, Object> map) {
        String account = map.get("account").toString();
        int effectiveTime = Integer.parseInt((String) map.get("effectiveTime"));
        if (account.equals("")) {
            return Result.error(CodeMsg.PARAMETER_ISNULL, "请输入账号");
        }
        int i = new Random().nextInt(1000000);
        String code = String.format("%06d", i);
        String message = "您的验证码为：" + code;

        int count = verifyCodeService.addVerifyCode(account, code, effectiveTime);
        if (count >= 0) {
            try {
                boolean success = mailService.sendSimpleMail(account, "验证码", message);
                if (success) {
                    return Result.success("验证码已发送至邮箱");
                }
            } catch (Exception e) {
                System.out.println(e);
                return Result.error(CodeMsg.FAIL, e.toString());
            }
        }
        return Result.error(CodeMsg.FAIL);
    }
}
