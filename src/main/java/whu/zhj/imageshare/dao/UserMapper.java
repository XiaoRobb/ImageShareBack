package whu.zhj.imageshare.dao;

import org.apache.ibatis.annotations.Mapper;
import whu.zhj.imageshare.model.Role;
import whu.zhj.imageshare.model.User;

/**
 * @program: ImageShare
 * @description:
 * @author: 周宏俊
 * @create: 2020-06-05 21:55
 */
@Mapper
public interface UserMapper {
    void insertUser(User user);
    User getUser(String username);
    User checkUser(String username, String password, Role role);
}
