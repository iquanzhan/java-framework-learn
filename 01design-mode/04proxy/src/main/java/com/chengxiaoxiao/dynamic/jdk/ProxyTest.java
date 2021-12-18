package com.chengxiaoxiao.dynamic.jdk;

import com.chengxiaoxiao.statics.Person;

/**
 * ProxyTest
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-17 17:38
 */
public class ProxyTest {
    public static void main(String[] args) {
        Person p = (Person) new JDKMeipo().getInstance(new Customer());
        p.findLove();
    }
}
