package com.wxhao.study.pattern.observer.gperadvice;

import java.util.Observable;

/**
 * JDK提供的一种观察者的实现方式，被观察者
 * 黑板,即被观察者
 */
public class Blackboard extends Observable {

    /**
     * 获取 作业的位置
     * @return
     */
    public String getHomeworkLocation(){
        return "右下方";
    }

    private static Blackboard blackboard = null;


    private Blackboard() {
        //大家只需要看一个黑板,所以单例,不同的班级可以有不同的黑板
    }

    public static Blackboard getInstance() {
        if (null == blackboard) {
            blackboard = new Blackboard();
        }
        return blackboard;
    }


    public void publishQuestion(Homework homework) {
        System.out.println(homework.getDiscipline() + "是:" + homework.getContent());
        //父类方法,标记黑板内容有更新
        setChanged();
        //通知所有看黑板的学生
        notifyObservers(homework);
    }
}
