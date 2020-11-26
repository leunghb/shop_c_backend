package shop.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.demo.entity.Address;
import shop.demo.mapper.AddressMapper;

import java.util.List;

@Transactional
@Service
public class AddressService {
    @Autowired
    private AddressMapper addressMapper;

    public List<Address> getAddressList(String account) {
        return addressMapper.getAddressList(account);
    }

    public int addAddress(String account, String name, String tel, String address, int isDefault) {
        return addressMapper.addAddress(account, name, tel, address, isDefault);
    }

    public int setAllAddressNonDefault(String account) {
        return addressMapper.setAllAddressNonDefault(account);
    }

    public int putAddress(String account, String name, String tel, String address, int isDefault, int id) {
        return addressMapper.putAddress(account, name, tel, address, isDefault, id);
    }

    public int delAddress(String account, int id) {
        return addressMapper.delAddress(account, id);
    }
}
