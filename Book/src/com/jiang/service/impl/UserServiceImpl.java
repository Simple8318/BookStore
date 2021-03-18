package com.jiang.service.impl;

import com.jiang.dao.UserDao;
import com.jiang.dao.impl.UserDaoImpl;
import com.jiang.pojo.User;
import com.jiang.service.UserService;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-09 11:06
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao=new UserDaoImpl();

    /**
     *      注册用户的业务方法
     * @param user  用户信息
     */
    @Override
    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    /**
     *      用户登录的业务方法
     * @param user      用户信息
     * @return      返回登录系统的用户的信息
     */
    @Override
    public User login(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    /**
     *      检查用户名是否可用的业务方法
     * @param username  用户名
     * @return  用户名可用返回false，用户名不可用返回true(用户名在数据库中未查询到，说明可用)
     */
    @Override
    public Boolean existsUsername(String username) {
        if (userDao.queryUserByUsername(username)==null){
            //如果数据库中用户名为null，则该用户名可用
            return false;
        }
        return true;
    }
}
