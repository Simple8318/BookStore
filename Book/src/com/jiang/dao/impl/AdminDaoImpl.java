package com.jiang.dao.impl;

import com.jiang.dao.AdminDao;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-16 17:14
 */
public class AdminDaoImpl extends BaseDao implements AdminDao {

    /**
     *      获取用户的权限
     * @param userId    用户Id
     * @return  返回用户的权限。若用户为管理员，则返回admin，佛足额返回null
     */
    @Override
    public String getAdminJurisdiction(Integer userId) {
        //language=MySQL
        String sql="select `jurisdiction` from t_user where id=?";
        return (String) queryForSingleValue(sql,userId);
    }
}
