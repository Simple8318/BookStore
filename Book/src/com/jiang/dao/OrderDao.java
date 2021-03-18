package com.jiang.dao;

import com.jiang.pojo.Order;

import java.util.List;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-16 13:51
 */
public interface OrderDao {
    public int saveOrder(Order order);
    public List<Order> queryOrders();
    public List<Order> queryOrderByUserId(Integer userId);
    public int getUserId(String userName);
}
