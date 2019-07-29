package com.wxhao.study.spring.v2.aop.aspect;

import com.wxhao.study.spring.v2.aop.intercept.MethodInterceptor;
import com.wxhao.study.spring.v2.aop.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * Created by Tom on 2019/4/15.
 */
public class AfterThrowingAdviceInterceptor extends AbstractAspectAdvice implements Advice, MethodInterceptor {


    private String throwingName;

    public AfterThrowingAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        try {
            return mi.proceed();
        } catch (Throwable e) {
            invokeAdviceMethod(mi, null, e.getCause());
            throw e;
        }
    }

    public void setThrowName(String throwName) {
        this.throwingName = throwName;
    }
}
