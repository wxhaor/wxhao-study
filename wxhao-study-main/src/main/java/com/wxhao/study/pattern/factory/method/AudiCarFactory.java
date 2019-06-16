package com.wxhao.study.pattern.factory.method;

import com.wxhao.study.pattern.factory.ICar;
import com.wxhao.study.pattern.factory.AudiCar;


/**
 * 奥迪工厂
 */
public class AudiCarFactory implements ICarFactory {

    @Override
    public ICar create() {
        return new AudiCar();
    }

}
