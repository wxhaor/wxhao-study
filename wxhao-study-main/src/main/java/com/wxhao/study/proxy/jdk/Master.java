package com.wxhao.study.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 捡屎官
 */
public class Master implements InvocationHandler {

    private Animal target; //被代理对象的引用作为一个成员变量保存下来了

    //获取被代理的动物
    public Object getInstance(Animal target) throws Exception {
        this.target = target;
        Class clazz = target.getClass();
        System.out.println("被代理对象的class是:" + clazz);
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("训练");
        //调用的时候
        method.invoke(this.target, args);
        System.out.println("铲屎");

        return null;
    }

}
