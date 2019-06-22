package com.wxhao.study.pattern.decorator.battercake.v2;


public abstract class BattercakeDecorator extends Battercake {

    //静态代理，委派
    private Battercake battercake;

    public BattercakeDecorator(Battercake battercake) {
        this.battercake = battercake;
    }

    protected abstract void doSomething();

    @Override
    public String getMsg() {
        return this.battercake.getMsg();
    }

    @Override
    public int getPrice() {
        return this.battercake.getPrice();
    }
}
