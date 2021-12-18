package com.chengxiaoxiao.factory.abstractfactory;

/**
 * 具体的实现工厂
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2021/1/6 14:05
 * @Version 1.0
 */
public class JavaCourseFactory implements CourseFactory {
    public IVideo createVideo() {
        return new JavaVideo();
    }

    public INote createNote() {
        return new JavaNote();
    }
}
