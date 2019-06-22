package com.wxhao.study.pattern.factory.simple;


import com.wxhao.study.pattern.factory.ICar;

import java.lang.reflect.Constructor;

/**
 * 简单工厂
 */
public class CarFactory {

    public ICar create(Class<? extends ICar> clazz) {
        //可以限定类
        try {
            if (null != clazz) {
                Constructor<? extends ICar> constructor;
                constructor = clazz.getDeclaredConstructor(String.class, String.class);
                //根据不同车来构建客户端需要的对象
                if ("AudiCar".equals(clazz.getSimpleName())) {
                    return constructor.newInstance("很帅", "很亮");
                } else if ("BenzCar".equals(clazz.getSimpleName())) {
                    return constructor.newInstance("很酷", "不亮");
                } else {
                    return null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
