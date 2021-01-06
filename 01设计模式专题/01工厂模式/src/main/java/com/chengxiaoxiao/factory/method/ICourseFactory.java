package com.chengxiaoxiao.factory.method;

import com.chengxiaoxiao.factory.simple.ICourse;

/**
 * 工厂方法接口
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2021/1/6 13:44
 * @Version 1.0
 */
public interface ICourseFactory {
    /**
     * 创建课程
     * @return
     */
    ICourse create();
}
