package com.chengxiaoxiao.factory.abstractfactory;

/**
 * 抽象工厂测试类
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2021/1/6 14:06
 * @Version 1.0
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        JavaCourseFactory javaCourseFactory = new JavaCourseFactory();
        javaCourseFactory.createVideo().record();
        javaCourseFactory.createNote().edit();
    }
}
