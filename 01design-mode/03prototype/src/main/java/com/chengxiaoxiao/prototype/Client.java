package com.chengxiaoxiao.prototype;

/**
 * 客户端
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-17 15:02
 */
public class Client {
    private Prototype prototype;

    public Client(Prototype prototype) {
        this.prototype = prototype;
    }
    public Prototype startClone(Prototype prototype){
        return prototype.clone();
    }
}
