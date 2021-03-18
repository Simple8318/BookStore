package com.jiang.service.impl;

import com.jiang.dao.AdminDao;
import com.jiang.dao.impl.AdminDaoImpl;
import com.jiang.service.AdminService;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-16 17:25
 */
public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao=new AdminDaoImpl();

    /**
     *      调用dao方法，获取用户的权限
     * @param userId    用户Id
     * @return  返回用户的权限。若用户为管理员，则返回admin，佛足额返回null
     */
    @Override
    public String getAdminJurisdiction(Integer userId) {
        return adminDao.getAdminJurisdiction(userId);
    }
}
