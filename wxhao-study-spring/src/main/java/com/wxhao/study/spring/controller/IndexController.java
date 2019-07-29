package com.wxhao.study.spring.controller;

import com.wxhao.study.spring.annotation.Autowired;
import com.wxhao.study.spring.annotation.Controller;
import com.wxhao.study.spring.annotation.RequestMapping;
import com.wxhao.study.spring.service.HelloService;

/**
 * @author wxhao
 * @date 2019/6/27
 */
@Controller
public class IndexController {

    @Autowired
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
