package com.chengxiaoxiao.test.staticproxy;

/**
 * 动态切换数据源
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-17 17:19
 */
public class DynamicDataSourceEntry {
    public final static String DEFAULT_SOURCE = null;
    private final static ThreadLocal<String> local = new ThreadLocal<String>();

    private DynamicDataSourceEntry() {
    }

    /**
     * 清空数据源
     */
    public static void clear() {
        local.remove();
    }

    /**
     * 获取当前使用的数据源名称
     * @return
     */
    public static String get() {
        return local.get();
    }

    /**
     * 还原当前切面的数据源
     */
    public static void restore(){
        local.set(DEFAULT_SOURCE);
    }

    /**
     * 设置已知名字的数据源
     * @param source
     */
    public static void set(String source){
        local.set(source);
    }

    /**
     * 根据年份统一设置数据源
     * @param year
     */
    public static void set(int year) {
        local.set("DB_"+year);
    }



}
