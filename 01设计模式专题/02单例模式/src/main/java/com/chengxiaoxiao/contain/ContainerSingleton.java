package com.chengxiaoxiao.contain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器缓存单例
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-17 14:39
 */
public class ContainerSingleton {
    private ContainerSingleton() {
    }

    private static Map<String, Object> ioc = new ConcurrentHashMap<String, Object>();

    public static Object getBean(String className) {
        synchronized (ioc) {
            if (!ioc.containsKey(className)) {
                Object bean = null;
                try {
                    bean = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                return bean;
            } else {
                return ioc.get(className);
            }
        }
    }
}
