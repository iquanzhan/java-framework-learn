package com.chengxiaoxiao.factory.abstractfactory;

/**
 * Python课程工厂
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2021/1/6 14:09
 * @Version 1.0
 */
public class PythonCourseFactory implements CourseFactory {

    public IVideo createVideo() {
        return new PythonVideo();
    }

    public INote createNote() {
        return new PythonNote();
    }
}
