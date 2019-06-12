package com.wxhao.study.proxy.jdk;

/**
 * 动物实现类
 */
public class Dog implements Animal {

	//1、两个角色：执行者、被代理对象
	//2、注重过程，必须要做，被代理对象没时间做或者不想做，不专业
	//3、执行者必须拿到被代理对象的个人资料（执行者持有被代理对象的引用）

	@Override
	public void lachouchou() {
		System.out.println("一坨坨");
	}





}
