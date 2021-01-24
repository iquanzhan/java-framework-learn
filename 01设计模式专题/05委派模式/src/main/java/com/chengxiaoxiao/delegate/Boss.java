package com.chengxiaoxiao.delegate;

/**
 * Boss
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-24 15:38
 */
public class Boss {
    public void command(String command,Leader leader){
        leader.doing(command);
    }
}
