package com.wxhao.study.pattern.decorator.battercake.v1;


public class BattercakeWithEgg extends Battercake{
    @Override
    public String getMsg() {
        return super.getMsg() + "+1个鸡蛋";
    }

    //加一个鸡蛋加1块钱
    @Override
    public int getPrice() {
        return super.getPrice() + 1;
    }
}
