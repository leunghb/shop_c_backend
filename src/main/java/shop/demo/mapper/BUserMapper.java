package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BUserMapper {
    int bLogin(String account, String password);
}
