package shop.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import shop.demo.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> getAllUser();
}
