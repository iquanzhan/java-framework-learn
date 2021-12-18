package com.chengxiaoxiao.observer.event;

/**
 * MouseEventTest
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-04-18 15:37
 */
public class MouseEventTest {
    public static void main(String[] args) {
        try {
            MouseEventCallback callback = new MouseEventCallback();
            //注册事件
            Mouse mouse = new Mouse();
            mouse.addListener(MouseEventType.ON_CLICK, callback);
            mouse.addListener(MouseEventType.ON_MOVE, callback);
            mouse.addListener(MouseEventType.ON_WHEEL, callback);
            mouse.addListener(MouseEventType.ON_OVER, callback);
            //调用方法
            mouse.click();
            //失焦事件
            mouse.blur();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
