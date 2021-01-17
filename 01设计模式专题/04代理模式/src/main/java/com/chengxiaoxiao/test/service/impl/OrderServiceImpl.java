package com.chengxiaoxiao.test.service.impl;

import com.chengxiaoxiao.test.entity.Order;
import com.chengxiaoxiao.test.dao.OrderDao;
import com.chengxiaoxiao.test.service.IOrderService;

/**
 * OrderServiceImpl
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-17 17:17
 */
public class OrderServiceImpl implements IOrderService {
    private OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public int createOrder(Order order) {
        return this.orderDao.insert(order);
    }
}
