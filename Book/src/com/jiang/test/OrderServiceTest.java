package com.jiang.test;

import com.jiang.pojo.Cart;
import com.jiang.pojo.CartItem;
import com.jiang.pojo.Order;
import com.jiang.service.OrderService;
import com.jiang.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-16 15:05
 */
public class OrderServiceTest {
    OrderService orderService=new OrderServiceImpl();

    @Test
    public void createOrder() {
        Cart cart=new Cart();
        cart.addItem(new CartItem(1,"11",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"11",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"22",1,new BigDecimal(200),new BigDecimal(200)));
        System.out.println("订单号是：" + orderService.createOrder(cart, 1));
    }

    @Test
    public void queryOrders() {
        List<Order> orders = orderService.queryOrders();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void queryOrderByUserId() {
        List<Order> orders = orderService.queryOrderByUserId(1);
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void getUserId() {
        int userId = orderService.getUserId("admin");
        System.out.println(userId);
    }
}