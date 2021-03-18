package com.jiang.test;

import com.jiang.dao.OrderItemDao;
import com.jiang.dao.impl.OrderItemDaoImpl;
import com.jiang.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-16 14:03
 */
public class OrderItemDaoTest {
    OrderItemDao orderItemDa=new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        orderItemDa.saveOrderItem(new OrderItem(null,"java1",1,new BigDecimal(100),new BigDecimal(100),"123"));
        orderItemDa.saveOrderItem(new OrderItem(null,"java2",2,new BigDecimal(200),new BigDecimal(400),"123"));
        orderItemDa.saveOrderItem(new OrderItem(null,"java3",3,new BigDecimal(300),new BigDecimal(900),"123"));
    }
}