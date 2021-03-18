package com.jiang.service;

import com.jiang.pojo.User;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-09 11:02
 */
public interface UserService {

    /**
     * 注册用户
     * @param user
     */
    public void registerUser(User user);

    /**
     * 登录
     * @param user
     * @return  如果返回null，则登录失败；否则登录成功
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return  返回true，则用户名已存在，不可用；否则，用户名可用
     */
    public Boolean existsUsername(String username);
}
