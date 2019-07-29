package com.wxhao.study.spring.service;

import com.wxhao.study.spring.annotation.Service;

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
