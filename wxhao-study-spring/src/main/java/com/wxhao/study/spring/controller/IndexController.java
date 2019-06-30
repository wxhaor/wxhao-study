package com.wxhao.study.spring.controller;

import com.wxhao.study.spring.core.annotation.Autowire;
import com.wxhao.study.spring.core.annotation.Controller;
import com.wxhao.study.spring.core.annotation.RequestMapping;
import com.wxhao.study.spring.service.HelloService;

/**
 * @author wxhao
 * @date 2019/6/27
 */
@Controller
public class IndexController {

    @Autowire
    private HelloService helloService;

    @RequestMapping("/")
    public String index(){
        return helloService.sayHello();
    }

    @RequestMapping("/x/.*")
    public String x(){
        return "x";
    }


}
