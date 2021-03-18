package com.jiang.test;

import com.jiang.pojo.Cart;
import com.jiang.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-15 14:48
 */
public class CartTest {
    Cart cart = new Cart();

    @Test
    public void addItem() {

        cart.addItem(new CartItem(1,"11",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"11",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"22",1,new BigDecimal(200),new BigDecimal(200)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        cart.addItem(new CartItem(1,"11",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"11",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"22",1,new BigDecimal(200),new BigDecimal(200)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        cart.addItem(new CartItem(1,"11",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"11",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"22",1,new BigDecimal(200),new BigDecimal(200)));
        cart.deleteItem(1);

        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        cart.addItem(new CartItem(1,"11",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(1,"11",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItem(2,"22",1,new BigDecimal(200),new BigDecimal(200)));
        cart.deleteItem(1);

        cart.clear();

        cart.addItem(new CartItem(1,"11",1,new BigDecimal(100),new BigDecimal(100)));

        cart.updateCount(1,10);
        System.out.println(cart);
    }
}