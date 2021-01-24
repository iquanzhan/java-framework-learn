package com.chengxiaoxiao.delegate;

/**
 * 员工A
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-24 15:34
 */
public class EmployeeA implements IEmployee {
    public void doing(String command) {
        System.out.printf("A:开始干" + command + "的活");
    }
}
