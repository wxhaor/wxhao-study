package com.wxhao.study.pattern.proxy.dynamicproxy.customproxy;

import java.lang.reflect.Method;


public interface MyInvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
