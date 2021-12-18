package com.chengxiaoxiao.factory.abstractfactory;

/**
 * JAVA视频录制类
 *
 * @Description
 * @Author Cheng XiaoXiao
 * @Date 2021/1/6 14:03
 * @Version 1.0
 */
public class JavaVideo implements IVideo {
    public void record() {
        System.out.printf("录制Java视频");
    }
}
