package shop.demo.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.demo.entity.VerifyCode;
import shop.demo.mapper.VerifyCodeMapper;

@Service
public class VerifyCodeService {
    @Autowired
    private VerifyCodeMapper verifyCodeMapper;

    public int addVerifyCode(String account, String code, int effectiveTime) {
        return verifyCodeMapper.addVerifyCode(account, code, effectiveTime);
    }
}
