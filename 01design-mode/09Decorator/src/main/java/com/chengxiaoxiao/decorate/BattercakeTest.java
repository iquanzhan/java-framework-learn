package com.chengxiaoxiao.decorate;

/**
 * 测试
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-04-18 14:52
 */
public class BattercakeTest {

    public static void main(String[] args) {
        Battercake battercake;

        battercake = new BaseBattercake();
        //加鸡蛋
        battercake = new EggDecorator(battercake);
        //加香肠
        battercake = new SausageDecorator(battercake);
        //加鸡蛋
        battercake = new EggDecorator(battercake);

        System.out.println(battercake.getMsg()+",总价："+battercake.getPrice());
    }
}
