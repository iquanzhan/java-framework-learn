package com.chengxiaoxiao.test.staticproxy;

import com.chengxiaoxiao.test.entity.Order;
import com.chengxiaoxiao.test.service.IOrderService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 业务逻辑层进行代理设置
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-17 17:25
 */
public class OrderServiceStaticProxy implements IOrderService {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");

    private IOrderService orderService;

    public OrderServiceStaticProxy(IOrderService orderService) {
        this.orderService = orderService;
    }

    public int createOrder(Order order) {
        before();
        Integer dbRouter = Integer.valueOf(dateFormat.format(new Date(order.getCreateTime())));
        DynamicDataSourceEntry.set(dbRouter);
        orderService.createOrder(order);
        after();
        return 0;
    }

    private void after() {
        System.out.println("之后的方法");
    }

    private void before() {
        System.out.println("之前的方法");
    }
}
