package com.chengxiaoxiao.observer.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 事件监听器，观察者的桥梁
 *
 * @Description:
 * @Author: Cheng XiaoXiao  (🍊 ^_^ ^_^)
 * @Date: 2021-04-18 15:24
 */
public class EventLisenter {
    protected Map<String, Event> events = new HashMap<String, Event>();

    //事件名称和目标对象来触发一个事件
    public void addListener(String eventType, Object target) {
        try {
            this.addListener(eventType,target,
                    target.getClass().getMethod("on"+toUpperFirstCase(eventType),Event.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void addListener(String eventType, Object target, Method callback) {
        //注册事件
        events.put(eventType, new Event(target, callback));
    }

    private void trigger(Event event){
        event.setSource(this);
        event.setTime(System.currentTimeMillis());
        if(event.getCallback()!=null){
            try {
                event.getCallback().invoke(event.getTarget(),event);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    protected void trigger(String trigger){
        if(!this.events.containsKey(trigger)){return;}
        Event event = this.events.get(trigger);
        event.setTrigger(trigger);
        trigger(event);
    }



    //逻辑处理的私有方法，首字母大写
    private String toUpperFirstCase(String str){
        char[] chars = str.toCharArray();
        chars[0] -= 32;
        return String.valueOf(chars);
    }


}
