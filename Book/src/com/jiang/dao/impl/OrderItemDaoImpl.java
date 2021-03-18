package com.jiang.dao.impl;

import com.jiang.dao.OrderItemDao;
import com.jiang.pojo.OrderItem;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-16 13:59
 */
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    /**
     *      保存订单项
     * @param orderItem     订单项信息
     * @return  返回影响的行数
     */
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        //language=MySQL
        String sql="insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values (?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }
}
