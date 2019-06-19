package com.wxhao.study.pattern.singleton.register;


/**
 * 枚举单例,可以规避序列化反序列化
 * 常量中去使用，常量不就是用来大家都能够共用吗？
 * 通常在通用API中使用
 */
public enum EnumSingleton {
    INSTANCE;
    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static EnumSingleton getInstance() {
        return INSTANCE;
    }
}
