package com.chengxiaoxiao.observer.jdk;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-04-18 15:13
 */
public class Teacher implements Observer {
    private String name;

    public Teacher(String name) {
        this.name = name;
    }

    public void update(Observable o, Object arg) {
        Gper gper = (Gper) o;
        Question question = (Question) arg;

        System.out.println(name + "老师您好\n" + "您周到一个来自" + gper.getName() + "的提问" +
                "问题内容如下：" + question.getContent() + "提问者：" + question.getUserName());

    }
}
