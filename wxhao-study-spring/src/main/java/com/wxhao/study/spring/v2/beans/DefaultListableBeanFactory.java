package com.wxhao.study.spring.v2.beans;

import com.wxhao.study.spring.v2.context.AbstractApplicationContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wxhao
 * @date 2019/7/14
 */
public class DefaultListableBeanFactory extends AbstractApplicationContext {

    protected final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

}
