package com.wxhao.study.pattern.observer.events.core;

import lombok.Data;
import lombok.ToString;

import java.lang.reflect.Method;

/**
 * 监听器的一种包装,标准事件源格式的定义
 */
@Data
@ToString
public class Event {
    //事件源，事件是由谁发起的保存起来
    private Object source;
    //事件触发，要通知谁
    private Object target;
    //事件触发，要做什么动作，回调
    private Method callback;
    //事件的名称，触发的是什么事件
    private String trigger;
    //事件触发的时间
    private long time;

    public Event setTrigger(String trigger) {
        this.trigger = trigger;
        return this;
    }

    public Event(Object target, Method callback) {

    }
}
