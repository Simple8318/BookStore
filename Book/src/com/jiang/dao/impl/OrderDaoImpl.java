package com.jiang.dao.impl;

import com.jiang.dao.OrderDao;
import com.jiang.pojo.Order;

import java.util.List;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-16 13:53
 */
public class OrderDaoImpl extends BaseDao implements OrderDao {
    /**
     *      保存订单
     * @param order     订单内容
     * @return  返回影响的行数
     */
    @Override
    public int saveOrder(Order order) {
        //language=MySQL
        String sql="insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    /**
     *      查询全部订单信息
     * @return  返回全部订单信息的List集合
     */
    @Override
    public List<Order> queryOrders() {
        //language=MySQL
        String sql="select `order_id` as orderId,`create_time` as createTime,`price`,`status`,`user_id` as userId from t_order";
        List<Order> orders = queryForList(Order.class, sql);
        return orders;
    }

    /**
     *      按用户ID查询订单信息
     * @param userId    用户ID
     * @return  返回该用户的所有订单信息
     */
    @Override
    public List<Order> queryOrderByUserId(Integer userId) {
        //language=MySQL
        String sql="select `order_id` as orderId,`create_time` as createTime,`price`,`status`,`user_id` as userId from t_order where `user_id`=?";
        List<Order> orders = queryForList(Order.class, sql, userId);
        return orders;
    }

    /**
     *      获取用户ID
     * @param userName  用户名称
     * @return  返回指定用户名的用户ID
     */
    @Override
    public int getUserId(String userName) {
        //language=MySQL
        String sql="select `id` from t_user where `username`=?";
        Integer userId = (Integer) queryForSingleValue(sql, userName);
        return userId;
    }
}
