package com.chengxiaoxiao.factory.method;

import com.chengxiaoxiao.factory.simple.ICourse;
import com.chengxiaoxiao.factory.simple.JavaCourse;

/**
 * Java课程的工厂类
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2021/1/6 13:45
 * @Version 1.0
 */
public class JavaCourseFactory implements ICourseFactory {
    public ICourse create() {
        return new JavaCourse();
    }
}
