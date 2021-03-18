package com.jiang.service;

import com.jiang.pojo.Cart;
import com.jiang.pojo.Order;

import java.util.List;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-16 14:52
 */
public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
    public List<Order> queryOrders();
    public List<Order> queryOrderByUserId(Integer userId);
    public int getUserId(String userName);
}
