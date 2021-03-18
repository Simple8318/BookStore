package com.jiang.test;

import com.jiang.dao.OrderDao;
import com.jiang.dao.impl.OrderDaoImpl;
import com.jiang.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-16 14:03
 */
public class OrderDaoTest {
    OrderDao orderDao=new OrderDaoImpl();

    @Test
    public void saveOrder() {
        orderDao.saveOrder(new Order("123",new Date(),new BigDecimal(100),0,1));
    }

    @Test
    public void queryOrders() {
        List<Order> orders = orderDao.queryOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void queryOrderByUserId() {
        List<Order> orders = orderDao.queryOrderByUserId(1);
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void getUserId() {
        int userId = orderDao.getUserId("admin");
        System.out.println(userId);
    }
}