package whu.zhj.imageshare.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import whu.zhj.imageshare.model.ResponseResult;
import whu.zhj.imageshare.model.Role;
import whu.zhj.imageshare.model.User;
import whu.zhj.imageshare.service.IUserService;
import whu.zhj.imageshare.util.JwtUtil;

import javax.annotation.Resource;

/**
 * @program: ImageShare
 * @description:
 * @author: 周宏俊
 * @create: 2020-05-30 16:50
 */
@RestController
@RequestMapping
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping("register")
    public ResponseResult register(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("role") int role){
        if(userService.getUser(username) == null){
            //没有相同用户名的用户，可以注册
            userService.addUser(username,password,Role.getEnum(role));
            return ResponseResult.success("注册成功");
        }
        return ResponseResult.fail("此用户名已被使用");
    }

    @PostMapping("login")
    public ResponseResult login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("role")int role){
        //密码验证
        User user = userService.checkUser(username, password, Role.getEnum(role));
        if(user != null){
            //生成 jwt token
            String token = JwtUtil.sign(user);
            if(token != null){
                userService.saveUser(user);
                return ResponseResult.success("成功", token);
            }
        }
        //用户名或密码错误
        return ResponseResult.fail("用户名或密码错误");
    }
}
