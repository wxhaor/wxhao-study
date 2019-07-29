package com.wxhao.study.spring.annotation;

import java.lang.annotation.*;

/**
 * @author wxhao
 * @date 2019/6/27
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {

    String name() default "";

}
