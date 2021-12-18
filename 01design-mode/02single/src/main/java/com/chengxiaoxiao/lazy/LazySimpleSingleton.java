package com.chengxiaoxiao.lazy;

/**
 * 饿汉式单例,线程不安全写法
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-16 14:42
 */
public class LazySimpleSingleton {
    private LazySimpleSingleton() {
    }

    private static LazySimpleSingleton lazy = null;

    /**
     * 线程不安全
     *
     * @return
     */
    public static LazySimpleSingleton getInstance() {
        if (lazy == null) {
            lazy = new LazySimpleSingleton();
        }
        return lazy;
    }

    /**
     * 加synchronized关键字,性能比较差
     *
     * @return
     */
    public synchronized static LazySimpleSingleton getInstance2() {
        if (lazy == null) {
            lazy = new LazySimpleSingleton();
        }
        return lazy;
    }

    /**
     * 使用双重检查锁,双重检查实例是否为null
     *
     * @return
     */
    public synchronized static LazySimpleSingleton getInstance3() {
        if (lazy == null) {
            synchronized (LazySimpleSingleton.class) {
                if (lazy == null) {
                    lazy = new LazySimpleSingleton();
                }
            }
        }
        return lazy;
    }


}
