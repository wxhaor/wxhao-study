package com.wxhao.study.pattern.decorator.battercake.v2;


public class BaseBattercake extends Battercake {
    @Override
    public String getMsg(){
        return "煎饼";
    }

    @Override
    public int getPrice(){
        return 5;
    }
}
