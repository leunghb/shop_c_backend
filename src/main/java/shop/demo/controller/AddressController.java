package shop.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.entity.Address;
import shop.demo.entity.CodeMsg;
import shop.demo.entity.Result;
import shop.demo.service.AddressService;
import shop.demo.utils.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 获取用户所有地址
     */
    @GetMapping("user/getAddressList")
    public Result<Object> getAddressList() {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        List<Address> list = addressService.getAddressList(account);
        return Result.success(list);
    }

    /**
     * 添加用户地址
     *
     * @param name      * String 姓名
     * @param tel       * String 电话
     * @param address   * String 地址
     * @param isDefault int 0-非默认 1-默认
     */
    @PostMapping("user/addAddress")
    public Result<Object> addAddress(@RequestParam(required = true) String name,
                                     @RequestParam(required = true) String tel,
                                     @RequestParam(required = true) String address,
                                     @RequestParam(required = false, defaultValue = "0") int isDefault) {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        if (account.length() == 0 || name.length() == 0 || tel.length() == 0 || address.length() == 0) {
            return Result.error(CodeMsg.PARAMETER_ISNULL);
        }
        int row = addressService.addAddress(account, name, tel, address, isDefault);
        if (!(row > 0)) {
            return Result.error(CodeMsg.FAIL, "添加失败");
        }
        return Result.success(CodeMsg.SUCCESS, "添加成功");
    }
}
