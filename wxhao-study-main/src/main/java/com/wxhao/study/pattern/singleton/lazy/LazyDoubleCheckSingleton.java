package com.wxhao.study.pattern.singleton.lazy;

/**
 * 双重检查线程安全的懒汉加载
 */
public class LazyDoubleCheckSingleton {

    private volatile static LazyDoubleCheckSingleton lazy = null;

    private LazyDoubleCheckSingleton() {
    }

    public static LazyDoubleCheckSingleton getInstance() {
        if (lazy == null) {
            synchronized (LazyDoubleCheckSingleton.class) {
                if (lazy == null) {
                    //可能有指令重排序 用 volatile 修饰,避免重排序
                    //1.分配内存给这个对象
                    //2.初始化对象
                    //3.设置lazy指向刚分配的内存地址
                    //4.初次访问对象
                    lazy = new LazyDoubleCheckSingleton();
                }
            }
        }
        return lazy;
    }
}
