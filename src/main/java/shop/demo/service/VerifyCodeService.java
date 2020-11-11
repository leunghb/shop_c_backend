package shop.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.demo.entity.VerifyCode;
import shop.demo.mapper.VerifyCodeMapper;

@Transactional
@Service
public class VerifyCodeService {
    @Autowired
    private VerifyCodeMapper verifyCodeMapper;

    public int addVerifyCode(String account, String code, int effectiveTime) {
        return verifyCodeMapper.addVerifyCode(account, code, effectiveTime);
    }

    public VerifyCode getVerifyCodeByAccount(String account) {
        return verifyCodeMapper.getVerifyCodeByAccount(account);
    }
}
