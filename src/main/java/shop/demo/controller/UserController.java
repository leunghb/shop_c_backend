package shop.demo.controller;

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
import shop.demo.utils.Md5;
import shop.demo.utils.TokenUtil;

import java.util.*;

import javax.servlet.http.HttpServletResponse;

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
     */
    @GetMapping("user/getAllUser")
    public Result<Object> getAllUser() {
        List<User> users = userService.getAllUser();
        return Result.success(users);
    }

    /**
     * 获取单个用户
     *
     * @param account * string 账号
     */
    @PostMapping("user/getUserByAccount")
    public Result<User> getUserByAccount(@RequestParam String account) {
        User user = userService.getUserByAccount(account);
        if (user == null) {
            return Result.error(CodeMsg.NOT_FIND_DATA);
        }
        return Result.success(user);
    }

    /**
     * 注册
     *
     * @param account  * string 账号
     * @param password * string 密码
     * @param code     * string 验证码
     */
    @PostMapping("user/register")
    public Result<Object> register(@RequestParam String account, @RequestParam String password,
                                   @RequestParam String code) {
        User user = userService.getUserByAccount(account);
        if (user != null)
            return Result.error(CodeMsg.FAIL, "账号已存在");

        HashMap map = checkCode(account, code);
        if (map.get("success").equals(false)) {
            String message = (String) map.get("message");
            return Result.success(CodeMsg.FAIL, message);
        }

        password = Md5.md5(password);
        int row = userService.addUser(account, password);
        if (!(row > 0))
            return Result.error(CodeMsg.FAIL, "注册失败");

        return Result.success(CodeMsg.SUCCESS, "注册成功");
    }

    public HashMap checkCode(String account, String code) {
        HashMap map = new HashMap();

        VerifyCode verifyCode = verifyCodeService.getVerifyCodeByAccount(account);
        if (verifyCode == null) {
            map.put("success", false);
            map.put("message", "账号与验证码不一致");
            return map;
        }

        int effectiveTime = verifyCode.getEffectiveTime();
        Date updatedAt = verifyCode.getUpdatedAt();
        Date nowTime = new Date();
        int time = effectiveTime * 60 * 1000;
        Date lastTime = new Date(updatedAt.getTime() + time);

        if (nowTime.compareTo(lastTime) > 0) {
            map.put("success", false);
            map.put("message", "验证码已失效");
            return map;
        }

        String correctVerifyCode = verifyCode.getCode();
        if (!code.equals(correctVerifyCode)) {
            map.put("success", false);
            map.put("message", "验证码错误");
            return map;
        }

        map.put("success", true);
        return map;
    }

    /**
     * 发送验证码
     *
     * @param account       * string 账号
     * @param effectiveTime int 有效时间（分钟）
     */
    @PostMapping("user/sendVerifyCode")
    public Result<Object> sendVerifyCode(@RequestParam String account,
                                         @RequestParam(required = false, defaultValue = "6") int effectiveTime) {
        if (account.equals(""))
            return Result.error(CodeMsg.PARAMETER_ISNULL, "请输入账号");

        int index = account.indexOf("@");
        if (!account.substring(index + 1, account.length()).equals("qq.com"))
            return Result.error(CodeMsg.FAIL, "仅支持QQ邮箱");

        int i = new Random().nextInt(1000000);
        String code = String.format("%06d", i);
        String message = "您的验证码为：" + code;

        int count = verifyCodeService.addVerifyCode(account, code, effectiveTime);
        if (count >= 0) {
            try {
                boolean success = mailService.sendSimpleMail(account, "验证码", message);
                if (success) {
                    return Result.success(CodeMsg.SUCCESS, "验证码已发送至邮箱");
                }
            } catch (Exception e) {
                System.out.println(e);
                return Result.error(CodeMsg.FAIL, e.toString());
            }
        }
        return Result.error(CodeMsg.FAIL);
    }

    /**
     * 登录
     *
     * @param account  * string 账号
     * @param password * string 密码
     */
    @PostMapping("user/login")
    public Result<Object> login(@RequestParam String account,
                                @RequestParam String password) {
        String passwordMd5 = Md5.md5(password);
        User user = userService.getUserByAccountAndPassword(account, passwordMd5);
        if (user == null) {
            return Result.error(CodeMsg.FAIL, "账号或密码错误");
        }

        String token = TokenUtil.token(account, password);

        return Result.success(token);
    }

    /**
     * 修改密码
     *
     * @param account  * string 账号
     * @param password * string 密码
     * @param code     * string 验证码
     */
    @PostMapping("user/updatePwd")
    public Result<Object> updatePwd(@RequestParam String account, @RequestParam String password,
                                    @RequestParam String code) {
        User user = userService.getUserByAccount(account);
        if (user == null)
            return Result.error(CodeMsg.FAIL, "账号不存在");

        HashMap map = checkCode(account, code);
        if (map.get("success").equals(false)) {
            String message = (String) map.get("message");
            return Result.success(CodeMsg.FAIL, message);
        }

        password = Md5.md5(password);
        int row = userService.putUserPwdByAccount(account, password);
        if (!(row > 0))
            return Result.error(CodeMsg.FAIL, "修改密码失败");

        return Result.success(CodeMsg.SUCCESS, "修改密码成功");
    }
}
