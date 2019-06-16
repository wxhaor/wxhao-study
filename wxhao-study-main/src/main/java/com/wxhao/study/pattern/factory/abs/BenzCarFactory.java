package com.wxhao.study.pattern.factory.abs;

/**
 * 奔驰工厂
 */
public class BenzCarFactory implements ICarProductFactory {

    @Override
    public ITire createTire() {
        return new BenzTire();
    }


    @Override
    public IEngine createEngine() {
        return new BenzEngine();
    }
}
