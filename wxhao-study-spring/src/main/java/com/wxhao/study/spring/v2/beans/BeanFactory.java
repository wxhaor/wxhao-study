package com.wxhao.study.spring.v2.beans;

/**
 * @author wxhao
 * @date 2019/7/14
 */
public interface BeanFactory {

    Object getBean(String name) throws Exception;

}
