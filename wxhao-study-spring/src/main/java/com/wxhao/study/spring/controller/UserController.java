package com.wxhao.study.spring.controller;

import com.wxhao.study.spring.core.annotation.Controller;
import com.wxhao.study.spring.core.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wxhao
 * @date 2019/6/27
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public String login(HttpServletRequest req) {
        return "登录成功!ip:" + req.getRemoteHost();
    }

}
