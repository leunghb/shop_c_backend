package shop.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.demo.mapper.BUserMapper;

@Transactional
@Service
public class BUserService {
    @Autowired
    private BUserMapper bUserMapper;

    public int bLogin(String account, String password) {
        return bUserMapper.bLogin(account, password);
    }
}
