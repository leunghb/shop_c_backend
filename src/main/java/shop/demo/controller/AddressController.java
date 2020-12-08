package shop.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shop.demo.config.UserLoginToken;
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
    @UserLoginToken
    @GetMapping("user/getAddressList")
    public Result<Object> getAddressList() {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        List<Address> list = addressService.getAddressList(account);
        return Result.success(list);
    }

    /**
     * 添加或更新用户地址
     *
     * @param name      * String 姓名
     * @param tel       * String 电话
     * @param address   * String 地址
     * @param isDefault int 0-非默认 1-默认
     * @param type      * int 0-添加 1-修改
     * @param id        int 地址id
     */
    @UserLoginToken
    @PostMapping("user/addOrPutAddress")
    public Result<Object> addAddress(@RequestParam String name,
                                     @RequestParam String tel,
                                     @RequestParam String address,
                                     @RequestParam(required = false, defaultValue = "0") int isDefault,
                                     @RequestParam int type,
                                     Integer id) {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        if (account.length() == 0 || name.length() == 0 || tel.length() == 0 || address.length() == 0) {
            return Result.error(CodeMsg.PARAMETER_ISNULL);
        }
        if (!(type == 0 || type == 1)) {
            return Result.error(CodeMsg.FAIL, "type必须为0或1");
        }
        if (isDefault == 1) {
            addressService.setAllAddressNonDefault(account);
        }
        int row = 0;
        if (type == 0) {
            row = addressService.addAddress(account, name, tel, address, isDefault);
        } else {
            if (!(id > 0)) {
                return Result.error(CodeMsg.FAIL, "地址id错误");
            }
            row = addressService.putAddress(account, name, tel, address, isDefault, id);
        }
        if (row == 0) {
            return Result.error(CodeMsg.FAIL, "添加失败");
        }
        return Result.success(CodeMsg.SUCCESS, type == 0 ? "添加成功" : "修改成功");
    }

    /**
     * 删除地址
     *
     * @param id      * int 地址id
     */
    @UserLoginToken
    @PostMapping("user/delAddress")
    public Result<Object> delAddress(@RequestParam Integer id) {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        if (id == null) {
            return Result.error(CodeMsg.PARAMETER_ISNULL);
        }
        int row = addressService.delAddress(account, id);
        if (row == 0) {
            return Result.error(CodeMsg.FAIL, "删除失败");
        }
        return Result.success(CodeMsg.SUCCESS, "删除成功");
    }

    /**
     * 获取单个地址
     *
     * @param id * int 地址id
     */
    @UserLoginToken
    @PostMapping("user/getAddress")
    public Result<Object> getAddress(@RequestParam Integer id) {
        String account = TokenUtil.getJwtToken(httpServletRequest);
        if (id == null) {
            return Result.error(CodeMsg.PARAMETER_ISNULL);
        }
        Address address = addressService.getAddress(account, id);
        return Result.success(address);
    }
}
