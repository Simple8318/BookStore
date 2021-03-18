package com.jiang.service.impl;

import com.jiang.dao.BookDao;
import com.jiang.dao.OrderDao;
import com.jiang.dao.OrderItemDao;
import com.jiang.dao.impl.BookDaoImpl;
import com.jiang.dao.impl.OrderDaoImpl;
import com.jiang.dao.impl.OrderItemDaoImpl;
import com.jiang.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-16 14:53
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao=new OrderDaoImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private BookDao bookDao=new BookDaoImpl();

    /**
     *      创建订单的业务方法
     * @param cart      购物车对象
     * @param userId    用户ID
     * @return      返回指定用户的购物车对象内容所构成的订单信息
     */
    @Override
    public String createOrder(Cart cart, Integer userId) {
        // 创建订单号(唯一)，时间戳+用户id
        String orderId=System.currentTimeMillis()+""+userId;
        // 创建订单对象
        Order order=new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        // 保存订单
        orderDao.saveOrder(order);
        // 遍历购物车中的每一个商品项转为订单项保存到数据库
        for (Map.Entry<Integer, CartItem> entry:cart.getItems().entrySet()){
            // 获取购物车中的每一个商品项
            CartItem cartItem=entry.getValue();
            // 将商品项转为订单项
            OrderItem orderItem=new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            // 将订单项保存到数据库
            orderItemDao.saveOrderItem(orderItem);

            // 更新图书数据库信息
            Book book=bookDao.queryBookById(cartItem.getId());
            // 更新效率
            book.setSales(book.getSales()+cartItem.getCount());
            // 更新库存
            book.setStock(book.getStock()-cartItem.getCount());
            // 将更新后的数据保存到数据库
            bookDao.updateBook(book);
        }
        // 结算后，情况购物车
        cart.clear();
        return orderId;
    }

    /**
     *      查询全部订单信息的业务方法
     * @return      返回全部订单信息的List集合
     */
    @Override
    public List<Order> queryOrders() {
        List<Order> orders = orderDao.queryOrders();
        return orders;
    }

    /**
     *      按用户ID查询订单的业务方法
     * @param userId    用户ID
     * @return      返回指定用户ID的该用户的全部订单信息
     */
    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
        List<Order> orders = orderDao.queryOrderByUserId(userId);
        return orders;
    }

    /**
     *      获取用户ID的业务方法
     * @param userName      用户名
     * @return      返回指定用户名的用户ID
     */
    @Override
    public int getUserId(String userName) {
        int userId = orderDao.getUserId(userName);
        return userId;
    }
}
