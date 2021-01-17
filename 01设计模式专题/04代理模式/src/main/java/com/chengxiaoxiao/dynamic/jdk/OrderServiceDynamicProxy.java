package com.chengxiaoxiao.dynamic.jdk;

import com.chengxiaoxiao.test.staticproxy.DynamicDataSourceEntry;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 动态代理实现数据源切换
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-17 17:42
 */
public class OrderServiceDynamicProxy implements InvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before(target);
        Object invoke = method.invoke(target, args);
        after();
        return invoke;
    }

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");

    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }


    private void after() {
        System.out.println("之后的方法");
    }

    private void before(Object target) {
        Integer dbRouter = null;
        try {
            dbRouter = Integer.valueOf(dateFormat.format(new Date((Long)target.getClass().getMethod("getCreateTime").invoke(target))));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        DynamicDataSourceEntry.set(dbRouter);
    }
}
