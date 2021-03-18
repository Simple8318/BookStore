package com.jiang.test;

import com.jiang.dao.AdminDao;
import com.jiang.dao.impl.AdminDaoImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-16 17:26
 */
public class AdminServiceTest {

    @Test
    public void getAdminJurisdiction() {
        AdminDao adminDao=new AdminDaoImpl();
        String adminJurisdiction = adminDao.getAdminJurisdiction(1);
        System.out.println(adminJurisdiction);
    }
}