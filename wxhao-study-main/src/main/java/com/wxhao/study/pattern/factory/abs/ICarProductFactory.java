package com.wxhao.study.pattern.factory.abs;

/**
 * 抽象工厂是用户的主入口
 * 在Spring中应用得最为广泛的一种设计模式
 * 易于扩展
 */
public interface ICarProductFactory {

    //对应产品族

    /**
     * 对应产品等级结构
     * @return
     */
    ITire createTire();
    /**
     * 对应产品等级结构
     * @return
     */
    IEngine createEngine();

    // 添加产品等级结构,不符合开闭原则,但是可以用于少量添加

}
