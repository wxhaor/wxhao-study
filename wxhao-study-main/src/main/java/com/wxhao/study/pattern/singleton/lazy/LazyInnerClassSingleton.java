package com.wxhao.study.pattern.singleton.lazy;

/**
 * 静态内部类懒汉式单例
 * 这种形式兼顾饿汉式的内存浪费，也兼顾synchronized性能问题
 * 完美地屏蔽了这两个缺点
 */
public class LazyInnerClassSingleton {

    /**
     * 默认使用LazyInnerClassGeneral的时候，会先初始化内部类
     * 如果没使用的话，内部类是不加载的
     */
    private LazyInnerClassSingleton() {
        //用于防止反射攻击
        if (LazyHolder.LAZY != null) {
            throw new RuntimeException("不允许创建多个实例");
        }
    }

    /**
     * static 是为了使单例的空间共享 保证这个方法不会被重写，重载
     *
     * @return
     */
    public static final LazyInnerClassSingleton getInstance() {
        //在返回结果以前，一定会先加载内部类
        return LazyHolder.LAZY;
    }

    /**
     * 默认不加载
     */
    private static class LazyHolder {
        private static final LazyInnerClassSingleton LAZY = new LazyInnerClassSingleton();
    }
}
