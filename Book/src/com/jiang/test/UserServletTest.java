package com.jiang.test;

import java.lang.reflect.Method;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-10 20:00
 */
public class UserServletTest {

    public void login(){
        System.out.println("这是login调用");
    }

    public void regist(){
        System.out.println("这是regist调用");
    }

    public void updateUser(){
        System.out.println("这是updateUser调用");
    }

    public void updateUserPassword(){
        System.out.println("这是updateUserPassword调用");
    }

    public static void main(String[] args) {
        String action="login";
        try {
            //根据参数获取相应方法的反射对象
            Method method = UserServletTest.class.getDeclaredMethod(action);
            //调用业务方法
            method.invoke(new UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
