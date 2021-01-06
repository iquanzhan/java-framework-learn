package com.chengxiaoxiao.factory.method;

/**
 * 工厂方法测试
 *
 * @Description 要使用那个类，就用那个类的创建工厂去创建。
 *              无需再通过ifelse去判断，如果有新增的课程，直接新增实现类就好了
 * @Author Cheng XiaoXiao
 * @Date 2021/1/6 13:46
 * @Version 1.0
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        ICourseFactory courseFactory = new JavaCourseFactory();
        courseFactory.create().record();
    }
}
