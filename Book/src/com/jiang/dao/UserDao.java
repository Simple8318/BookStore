package com.jiang.dao;

import com.jiang.pojo.User;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-09 9:39
 */
public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username  用户名
     * @return  如果返回null，说明数据库中没有该用户名的用户；否则，返回用户信息
     */
    public User queryUserByUsername(String username);

    /**
     * 根据用户名和对于密码查询用户信息
     * @param username
     * @param password
     * @return  如果返回null，说明用户名或密码错误；否则，返回用户信息
     */
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 保存用户信息
     * @param user  User对象
     * @return 返回-1，则sql查询失败；否则，返回sql影响的行数
     */
    public int saveUser(User user);
}
