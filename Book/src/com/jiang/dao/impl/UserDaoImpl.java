package com.jiang.dao.impl;

import com.jiang.dao.UserDao;
import com.jiang.pojo.User;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-09 9:46
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    /**
     *      按用户名查询用户信息
     * @param username  用户名
     * @return  返回指定用户名的用户信息
     */
    @Override
    public User queryUserByUsername(String username) {
        //language=MySQL
        String sql="select `id`,`username`,`password`,`email` from t_user where username=?";
        return queryForOne(User.class,sql,username);
    }

    /**
     *      按用户名和密码查询用户信息
     * @param username  用户名
     * @param password  用户密码
     * @return  返回用户名和密码都正确的指定用户的用户信息
     */
    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        //language=MySQL
        String sql="select `id`,`username`,`password`,`email` from t_user where username=? and password=?";
        return queryForOne(User.class,sql,username,password);
    }

    /**
     *      保存用户信息
     * @param user  用户信息
     * @return  返回影响的行数
     */
    @Override
    public int saveUser(User user) {
        //language=MySQL
        String sql="insert into t_user(`username`,`password`,`email`) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
