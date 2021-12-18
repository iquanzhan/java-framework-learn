package com.chengxiaoxiao.observer.jdk;

/**
 * ObserverTest
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-04-18 15:16
 */
public class ObserverTest {
    public static void main(String[] args) {
        Gper gper = Gper.getInstance();
        Teacher tom = new Teacher("tom");
        Teacher mic = new Teacher("mic");
        gper.addObserver(tom);
        gper.addObserver(mic);

        Question question = new Question();
        question.setUserName("小明");
        question.setContent("观察者模式适用场景");
        gper.publishQuestion(question);



    }
}
