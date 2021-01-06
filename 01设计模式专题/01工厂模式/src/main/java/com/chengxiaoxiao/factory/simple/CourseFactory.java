package com.chengxiaoxiao.factory.simple;

/**
 * 课程创建工厂类
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2021/1/6 13:18
 * @Version 1.0
 */
public class CourseFactory {
    /**
     * 创建对象
     * @param name 名称
     * @return
     */
    public ICourse create(String name){
        if("java".equals(name)){
            return new JavaCourse();
        }
        return null;
    }
}
