package com.wxhao.study.spring.service;

import com.wxhao.study.spring.core.annotation.Service;

/**
 * @author wxhao
 * @date 2019/6/27
 */
@Service
public class HelloService {

    public String sayHello(){
        return "HelloWorld";
    }

}
