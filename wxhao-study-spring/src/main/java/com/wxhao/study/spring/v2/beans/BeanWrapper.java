package com.wxhao.study.spring.v2.beans;

/**
 * @author wxhao
 * @date 2019/7/27
 */
public class BeanWrapper {

    private Object wrappedInstance;
    private Class<?> wrappedClass;
    public BeanWrapper(Object wrappedInstance){
        this.wrappedInstance = wrappedInstance;
    }
    public Object getWrappedInstance(){
        return this.wrappedInstance;
    }
    // 返回代理以后的 Class
// 可能会是这个 $Proxy0
    public Class<?> getWrappedClass(){
        return this.wrappedInstance.getClass();
    }

}
