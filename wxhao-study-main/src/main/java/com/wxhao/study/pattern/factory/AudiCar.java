package com.wxhao.study.pattern.factory;



public class AudiCar implements ICar {

    @Override
    public void create() {
        System.out.println("奥迪");
    }

}
