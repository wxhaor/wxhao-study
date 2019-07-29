package com.wxhao.study.spring.controller;

import com.wxhao.study.spring.annotation.Controller;
import com.wxhao.study.spring.annotation.RequestMapping;
import com.wxhao.study.spring.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wxhao
 * @date 2019/6/27
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public String login(HttpServletRequest req,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password) {
        String queryString = req.getQueryString();
        System.out.println(queryString);
        return "登录成功!,用户名为:" + username + ",password:" + password;
    }

}
