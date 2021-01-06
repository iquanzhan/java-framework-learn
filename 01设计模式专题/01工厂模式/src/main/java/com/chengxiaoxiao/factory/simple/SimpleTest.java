package com.chengxiaoxiao.factory.simple;

/**
 * 简单工厂测试类
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2021/1/6 13:20
 * @Version 1.0
 */
public class SimpleTest {
    public static void main(String[] args) {
        new CourseFactory().create("java").record();
    }
}
