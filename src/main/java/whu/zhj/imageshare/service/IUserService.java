package whu.zhj.imageshare.service;

import whu.zhj.imageshare.model.Role;
import whu.zhj.imageshare.model.User;

/**
 * @program: ImageShare
 * @description: 用户服务接口
 * @author: 周宏俊
 * @create: 2020-05-30 16:56
 */
public interface IUserService {
    /**
     * 校验用户信息
     * @param username 用户名
     * @param password 用户密码
     * @return 校验结果
     */
    User checkUser(String username, String password, Role role);

    /**
     * 查询用户信息
     * @param username 用户名
     * @return 用户
     */
    User getUser(String username);

    /**
     * 添加用户
     * @param username 用户名
     * @param password 用户密码
     * @param role 用户身份
     */
    void addUser(String username, String password, Role role);

    void saveUser(User user);


}
