package com.wxhao.study.pattern.factory;

import com.wxhao.study.pattern.factory.abs.AudiCarProductFactory;
import com.wxhao.study.pattern.factory.abs.ICarProductFactory;
import com.wxhao.study.pattern.factory.method.AudiCarFactory;
import com.wxhao.study.pattern.factory.method.ICarFactory;
import com.wxhao.study.pattern.factory.simple.CarFactory;
import org.junit.Test;

/**
 * 工厂模式
 *
 * @author wxhao
 * @date 2019/6/16
 */
public class FactoryTest {

    /**
     * 简单工厂模式
     */
    @Test
    public void testSimpleFactory() {

        //按照我们普通的写法,会把造车需要的零件在应用层初始化
        //父类 ICar 指向子类 AudiCar 的引用，应用层代码需要依赖 AudiCar
        //继续增加 BenzCar 甚至更多,客户端的依赖会变得越来越臃肿(依赖了很多很多的品牌车)
        //而且现在的创建 所需要需要车轱辘和大灯很好提供,如果很复杂那就不易于扩展,
        //而零件也可能因为需求的改变由简单之后又改的比较复杂
        ICar iCar = new AudiCar("很帅", "很亮");
        iCar.create();

        //为了更好的可读性,做出以下修改
        CarFactory factory = new CarFactory();
        ICar iCar1 = factory.create(BenzCar.class);
        iCar1.create();

    }

    @Test
    public void testFactoryMethod() {
        //工厂方法
        //获取奥迪工厂
        ICarFactory factory = new AudiCarFactory();
        //获取造车材料
        ICar iCar = factory.provide();
        //运行造车方法
        iCar.create();

    }

    @Test
    public void testSbstractFactory() {

        ICarProductFactory factory = new AudiCarProductFactory();
        factory.createTire().gululu();
        factory.createEngine().hulonglong();

    }


}
