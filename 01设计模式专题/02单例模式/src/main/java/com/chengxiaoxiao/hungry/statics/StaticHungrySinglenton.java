package com.chengxiaoxiao.hungry.statics;

/**
 * 饿汉式静态代码块形式
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-16 14:39
 */
public class StaticHungrySinglenton {
    private final static StaticHungrySinglenton staticHungrySinglenton;

    static {
        staticHungrySinglenton = new StaticHungrySinglenton();
    }

    private StaticHungrySinglenton() {
    }

    public static StaticHungrySinglenton getInstance() {
        return staticHungrySinglenton;
    }
}
