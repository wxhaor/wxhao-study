package com.wxhao.study.spring.v2.aop;

/**
 * Created by Tom.
 */
public interface AopProxy {


    Object getProxy();


    Object getProxy(ClassLoader classLoader);
}
