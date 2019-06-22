package com.wxhao.study.pattern.observer;

import com.google.common.eventbus.EventBus;
import com.wxhao.study.pattern.observer.events.mouseevent.Mouse;
import com.wxhao.study.pattern.observer.events.mouseevent.MouseEventCallback;
import com.wxhao.study.pattern.observer.events.mouseevent.MouseEventType;
import com.wxhao.study.pattern.observer.gperadvice.Blackboard;
import com.wxhao.study.pattern.observer.gperadvice.Homework;
import com.wxhao.study.pattern.observer.gperadvice.Student;
import com.wxhao.study.pattern.observer.guava.GuavaEvent;
import org.junit.Test;

/**
 * 观察者模式
 *
 * @author wxhao
 * @date 2019/6/20
 */
public class TestObserve {

    @Test
    public void testObserver() {
        //JDK实现观察者模式
        Blackboard blackboard = Blackboard.getInstance();
        Student xiaom = new Student("小明");
        Student xiaoh = new Student("小红");

        //准备发布一条作业
        Homework homework = new Homework();
        homework.setDiscipline("语文作业");
        homework.setContent("把名字写100遍");

        //让同学们看黑板
        blackboard.addObserver(xiaom);
        //如果同学没有看黑板,则不知道作业是什么
        //blackboard.addObserver(xiaoh);

        //在黑板上发布作业
        blackboard.publishQuestion(homework);

    }

    @Test
    public void testMouseEvent() {
        //自己实现监听器
        MouseEventCallback callback = new MouseEventCallback();

        Mouse mouse = new Mouse();

        //@谁？  @回调方法
        mouse.addLisenter(MouseEventType.ON_CLICK, callback);
        mouse.addLisenter(MouseEventType.ON_FOCUS, callback);

        mouse.click();

        mouse.focus();

    }

    @Test
    public void testGuavaEvent() {
        //消息总线
        EventBus eventBus = new EventBus();
        GuavaEvent guavaEvent = new GuavaEvent();
        eventBus.register(guavaEvent);
        eventBus.post("Tom");

        //从Struts到SpringMVC的升级
        //因为Struts面向的类，而SpringMVC面向的是方法

        //前面两者面向的是类，Guava面向是方法

        //能够轻松落地观察模式的一种解决方案
        //MQ
    }


}
