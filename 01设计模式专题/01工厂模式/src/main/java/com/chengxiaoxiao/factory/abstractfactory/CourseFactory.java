package com.chengxiaoxiao.factory.abstractfactory;

/**
 * 抽象工厂
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2021/1/6 14:02
 * @Version 1.0
 */
public interface CourseFactory {
    IVideo createVideo();
    INote createNote();
}
