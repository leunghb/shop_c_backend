package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.demo.entity.Address;

import java.util.List;

@Mapper
public interface AddressMapper {
    List<Address> getAddressList(String account);

    int addAddress(String account, String name, String tel, String address, int isDefault);
}
