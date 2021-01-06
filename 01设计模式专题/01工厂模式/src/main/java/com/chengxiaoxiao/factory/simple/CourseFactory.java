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
    public static ICourse create(String name){
        if("java".equals(name)){
            return new JavaCourse();
        }
        return null;
    }

    /**
     * 创建对象 V2.0
     * @param className class全限定名称，此时可以随便输入classname
     * @return
     */
    public static ICourse createV2(String className) throws Exception {
        if(!(className==null||"".equals(className))){
            return (ICourse)Class.forName(className).newInstance();
        }
        return null;
    }

    /**
     * 创建对象 V3.0
     * @param clazz class对象
     * @return
     */
    public static ICourse createV3(Class<? extends ICourse> clazz) throws Exception {
       if(clazz!=null){
           return clazz.newInstance();
       }
        return null;
    }




}
