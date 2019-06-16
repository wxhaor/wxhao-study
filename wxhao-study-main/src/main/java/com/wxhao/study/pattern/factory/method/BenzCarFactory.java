package com.wxhao.study.pattern.factory.method;

import com.wxhao.study.pattern.factory.ICar;
import com.wxhao.study.pattern.factory.BenzCar;

/**
 * 奔驰工厂
 */
public class BenzCarFactory implements ICarFactory {

    @Override
    public ICar create() {
        return new BenzCar();
    }
}
