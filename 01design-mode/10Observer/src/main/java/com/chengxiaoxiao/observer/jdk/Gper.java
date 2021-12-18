package com.chengxiaoxiao.observer.jdk;

import java.util.Observable;

/**
 * JDK实现的一种观察者的实现方式
 * 被观察者
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-04-18 15:08
 */
public class Gper extends Observable {
    private String name="GPer生态圈";

    private static Gper gper = null;

    private Gper() {
    }

    public String getName() {
        return name;
    }

    public static Gper getInstance(){
        if(null==gper){
            gper = new Gper();
        }
        return gper;
    }

    public void publishQuestion(Question question){
        System.out.println(question.getUserName()+"在"+this.name+"上提交了一个问题。");
        setChanged();
        notifyObservers(question);
    }

}
