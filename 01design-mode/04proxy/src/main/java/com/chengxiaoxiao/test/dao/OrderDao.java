package com.chengxiaoxiao.test.dao;

import com.chengxiaoxiao.test.entity.Order;

/**
 * OrderDao
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-17 17:15
 */
public class OrderDao {
    public int insert(Order order) {
        System.out.println("OrderDao创建订单成功");
        return 1;
    }
}
