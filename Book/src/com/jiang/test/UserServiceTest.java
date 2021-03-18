package com.jiang.test;

import com.jiang.pojo.User;
import com.jiang.service.UserService;
import com.jiang.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-09 11:10
 */
public class UserServiceTest {

    UserService userService=new UserServiceImpl();

    @Test
    public void registerUser() {
        userService.registerUser(new User(null,"user001","123456","user001@qq.com"));
        userService.registerUser(new User(null,"user002","123456","user002@qq.com"));
    }

    @Test
    public void login() {
        if (userService.login(new User(null, "admin", "admin", null))==null){
            System.out.println("登录失败");
        }else {
            System.out.println("登录成功");
        }
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("admin001")){
            System.out.println("用户名已存在，不可用");
        } else {
            System.out.println("用户名可用");
        }
    }
}