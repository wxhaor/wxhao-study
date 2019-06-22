package com.wxhao.study.pattern.factory.method;

import com.wxhao.study.pattern.factory.AudiCar;
import com.wxhao.study.pattern.factory.ICar;


/**
 * 奥迪工厂
 */
public class AudiCarFactory implements ICarFactory {

    @Override
    public ICar provide() {
        return new AudiCar("很帅", "很亮");
    }

}
