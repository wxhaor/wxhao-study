package com.wxhao.study.pattern.decorator.battercake.v1;


public class BattercakeWithEggAndSausage extends BattercakeWithEgg{
    @Override
    public String getMsg() {
        return super.getMsg() + "+1根香肠";
    }

    @Override
    //加一个香肠加2块钱
    public int getPrice() {
        return super.getPrice() + 2;
    }
}
