package com.chengxiaoxiao.statics;

/**
 * 父亲给儿子找对象
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-01-17 15:16
 */
public class Father {
    private Son son;

    public Father(Son son) {
        this.son = son;
    }
    public void findLove(){
        System.out.println("棒儿子找对象");
        this.son.findLove();
    }
}
