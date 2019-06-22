package com.wxhao.study.pattern.observer.gperadvice;

import lombok.AllArgsConstructor;

import java.util.Observable;
import java.util.Observer;

/**
 * 学生(观察者),需要看黑板上的作业
 */
@AllArgsConstructor
public class Student implements Observer {

    /**
     * 学生的名字,用于区分是哪个学生
     */
    private String name;

    /**
     * 学生看到黑板上的作业之后需要干的事情
     *
     * @param o   被观察者(黑板)
     * @param arg 消息(发布的作业)
     */
    @Override
    public void update(Observable o, Object arg) {
        Blackboard blackboard = (Blackboard) o;
        Homework homework = (Homework) arg;
        System.out.println(this.name + "抬头看到了写在黑板" + blackboard.getHomeworkLocation() + "的作业");
        System.out.println("并完成了" + homework.getDiscipline() + homework.getContent());
    }
}
