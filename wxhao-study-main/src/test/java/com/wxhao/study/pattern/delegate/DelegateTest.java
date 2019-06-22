package com.wxhao.study.pattern.delegate;

import com.wxhao.study.pattern.delegate.simple.Boss;
import com.wxhao.study.pattern.delegate.simple.Leader;
import org.junit.Test;

/**
 * 委派模式
 * @author wxhao
 * @date 2019/6/20
 */
public class DelegateTest {



    @Test
    public void testDelegate() {
        //客户请求（Boss）、委派者（Leader）、被被委派者（Target）
        //委派者要持有被委派者的引用
        //代理模式注重的是过程， 委派模式注重的是结果
        //策略模式注重是可扩展（外部扩展），委派模式注重内部的灵活和复用
        //委派的核心：就是分发、调度、派遣

        //委派模式：就是静态代理和策略模式一种特殊的组合

        new Boss().command("登录",new Leader());
    }

/*
<?xml version="1.0" encoding="UTF-8"?>
    <web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://java.sun.com/xml/ns/j2ee" xmlns:javaee="http://java.sun.com/xml/ns/javaee"
	xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
	xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd"
	version="2.4">
	<display-name>Gupao Web Application</display-name>


    <servlet>
		<servlet-name>delegateServlet</servlet-name>
		<servlet-class>com.wxhao.study.pattern.delegate.mvc.DispatcherServlet</servlet-class>
		<load-on-startup>1</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>delegateServlet</servlet-name>
		<url-pattern>/*</url-pattern>
	</servlet-mapping>
</web-app>
*/

}
