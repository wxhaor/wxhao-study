package com.wxhao.study.pattern.factory.abs;


public class AudiCarFactory implements ICarProductFactory {

    @Override
    public ITire createTire() {
        return new AudiTire();
    }

    @Override
    public IEngine createEngine() {
        return new AudiEngine();
    }
}
