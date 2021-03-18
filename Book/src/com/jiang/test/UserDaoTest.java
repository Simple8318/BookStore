package com.jiang.test;

import com.jiang.dao.UserDao;
import com.jiang.dao.impl.UserDaoImpl;
import com.jiang.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-09 10:01
 */
public class UserDaoTest {
    UserDao userDao=new UserDaoImpl();

    @Test
    public void queryUserByUsername() {
        if (userDao.queryUserByUsername("admin11")==null){
            System.out.println("用户名可用！");
        } else {
            System.out.println("用户名已存在！");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        if (userDao.queryUserByUsernameAndPassword("admin","admin1")==null){
            System.out.println("用户名或密码错误，登录失败");
        } else {
            System.out.println("登录成功");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null, "jiang", "123456", "804041471@qq.com")));
    }
}