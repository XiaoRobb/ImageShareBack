package whu.zhj.imageshare.service.impl;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import whu.zhj.imageshare.dao.UserMapper;
import whu.zhj.imageshare.dao.UserRepository;
import whu.zhj.imageshare.model.Role;
import whu.zhj.imageshare.model.User;
import whu.zhj.imageshare.service.IUserService;

import javax.annotation.Resource;

/**
 * @program: ImageShare
 * @description:
 * @author: 周宏俊
 * @create: 2020-05-30 16:58
 */
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserRepository repo;
    @Resource
    private UserMapper mapper;

    /**
     * 检查用户的用户名和密码
     * @param username 用户名
     * @param password 用户密码
     * @return
     */
    @Override
    public User checkUser(String username, String password, Role role) {
        return mapper.checkUser(username, password, role);
    }

    /**
     * 从mysql数据库中根据用户名获得用户
     * @param username 用户名
     * @return
     */
    @Override
    public User getUser(String username) {
        User user = repo.findByUsername(username);
        if(user == null){
            user = mapper.getUser(username);
        }
        return user;
    }

    /**
     * 加入用户到mysql数据库中
     * @param username 用户名
     * @param password 用户密码
     * @param role 用户身份
     */
    @Override
    public void addUser(String username, String password, Role role){
        User user = new User(username, password, role);
        mapper.insertUser(user);

    }

    /**
     * 加入用户到Ignite缓存中
     * @param user
     */
    @Override
    public void saveUser(User user){
        if(repo.findByUsername(user.getUsername()) == null){
            repo.save(user.getId(), user);
        }
    }
}
