package com.jiang.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-10 20:37
 */
public class WebUtils {
    /**
     *      一次性获取请求参数，并注入到JavaBean中
     * @param value     获取的请求参数，以Map集合的形式注入JavaBean中
     * @param bean      需要注入到的JavaBean对象
     * @param <T>       返回值为泛型类型，避免后期类型转换，且更适用
     * @return      返回需要的JavaBean对象
     */
    public static <T> T copyParameterToBean(Map value, T bean){
        try {
            // System.out.println("注入之前:" + bean);
            // 通过第三方工具类的方法，把所有请求的参数都注入到user对象中
            BeanUtils.populate(bean,value);
            // System.out.println("注入之后:" + bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    /**
     *      将字符串转换成int类型的数据
     * @param str   字符串数据
     * @param defaultValue     转换后的int类型数据
     * @return  转换成功返回转换后的int数据；否则返回defaultValue默认值
     */
    public static int parserInt(String str,int defaultValue){
        if (str==null){
            str=String.valueOf(defaultValue);
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }
}
