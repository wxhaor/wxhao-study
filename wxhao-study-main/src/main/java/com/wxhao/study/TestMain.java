package com.wxhao.study;

import com.wxhao.study.proxy.MProxy;

/**
 * @author wxhao
 * @date 2019/6/8
 */
public class TestMain {

    public static void main(String[] args) {
        Class<TestMain> testMainClass = TestMain.class;
        new MProxy().newProxyInstance(testMainClass.getClassLoader(),
                testMainClass.getInterfaces(), null);

        //生成class文件
        //ProxyGenerator.generateProxyClass();
    }
}
