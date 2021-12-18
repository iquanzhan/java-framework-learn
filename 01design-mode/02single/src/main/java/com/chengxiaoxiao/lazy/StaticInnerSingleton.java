package com.chengxiaoxiao.lazy;

import java.io.Serializable;

/**
 * 懒汉式-静态内部类的方式
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-16 14:49
 */
public class StaticInnerSingleton implements Serializable {
    private StaticInnerSingleton() {
        //禁止反射创建
        if (LazyHolder.LAZY != null) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    public static final StaticInnerSingleton getInstance() {
        return LazyHolder.LAZY;
    }

    private static class LazyHolder {
        private static final StaticInnerSingleton LAZY = new StaticInnerSingleton();
    }

    /**
     * 防止序列化
     *
     * @return
     */
    private Object readResolve() {
        return LazyHolder.LAZY;
    }
}
