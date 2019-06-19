package com.wxhao.study.pattern.proxy.staticproxy;

import com.wxhao.study.pattern.proxy.Person;


public class Son implements Person {

    @Override
    public void findLove() {
        System.out.println("儿子要求：肤白貌美大长腿");
    }

    public void findJob() {

    }

    public void eat() {

    }

}
