package com.jiang.pojo;

/**
 * @author jiang
 * @email 804041471@qq.com
 * @create 2021-03-15 14:24
 */

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  购物车对象
 */
public class Cart {

    private Map<Integer,CartItem> items=new LinkedHashMap<>();

    /**
     *  添加商品项
     * @param cartItem
     */
    public void addItem(CartItem cartItem){
        // 1 查看购物车中是否有该商品的信息
        CartItem item = items.get(cartItem.getId());
        // 2 如果当前购物车中没有要添加的商品项的信息
        if (item==null){
            // 3 将商品项信息添加至购物车
            items.put(cartItem.getId(),cartItem);
        } else {    // 4 如果购物车中已经存在该商品
            // 5 更新
            // 商品项的数量累加
            item.setCount(item.getCount()+1);
            // 更新该该商品的总金额
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }

    }

    /**
     *  删除商品项
     * @param id
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }

    /**
     * 情况购物车
     */
    public void clear(){
        items.clear();
    }

    /**
     *  修改商品数量
     * @param id
     * @param count
     */
    public void updateCount(Integer id,Integer count){
        // 1 查看购物车中是否有该商品
        CartItem cartItem = items.get(id);
        if (cartItem!=null){    // 如果购物车中有该商品，更新
            // 更新数量
            cartItem.setCount(count);
            // 更新该商品的总金额
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    public Integer getTotalCount() {
        Integer totalCount=0;
        // 遍历购物车中的商品，对数量进行累加
        for (Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice=new BigDecimal(0);
        // 遍历购物车中的每种商品，对金额进行总和
        for (Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalPrice=totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
