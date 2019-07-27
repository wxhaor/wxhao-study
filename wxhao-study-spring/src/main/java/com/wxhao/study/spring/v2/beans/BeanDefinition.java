package com.wxhao.study.spring.v2.beans;

import lombok.Data;

/**
 * @author wxhao
 * @date 2019/7/14
 */
@Data
public class BeanDefinition {

    private String beanClassName;
    private boolean lazyInit = false;
    private String factoryBeanName;

}
