package com.wxhao.study.proxy.custom;

import com.wxhao.study.proxy.jdk.Animal;

import java.lang.reflect.Method;

public class CustomMaster implements MyInvocationHandler {

    private Animal target;

    //获取被代理的动物
    public Object getInstance(Animal target) throws Exception {
        this.target = target;
        Class clazz = target.getClass();
        System.out.println("被代理对象的class是:" + clazz);
        return MProxy.newProxyInstance(new MyClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("训练");
        method.invoke(this.target, args);
        System.out.println("铲屎");
        return null;
    }

}
