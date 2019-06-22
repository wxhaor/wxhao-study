package com.wxhao.study.pattern.factory.method;

import com.wxhao.study.pattern.factory.BenzCar;
import com.wxhao.study.pattern.factory.ICar;

/**
 * 奔驰工厂
 */
public class BenzCarFactory implements ICarFactory {

    @Override
    public ICar provide() {
        return new BenzCar("很酷", "不亮");
    }
}
