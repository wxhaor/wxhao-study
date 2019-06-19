package com.wxhao.study.pattern.singleton.hungry;


/**
 * 饿汉式静态块单例
 */
public class HungryStaticSingleton {
    private static final HungryStaticSingleton HUNGRY_SINGLETON;

    static {
        HUNGRY_SINGLETON = new HungryStaticSingleton();
    }

    private HungryStaticSingleton() {
    }

    public static HungryStaticSingleton getInstance() {
        return HUNGRY_SINGLETON;
    }
}
