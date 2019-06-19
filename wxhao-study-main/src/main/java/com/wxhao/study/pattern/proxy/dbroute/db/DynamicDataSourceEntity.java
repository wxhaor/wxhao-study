package com.wxhao.study.pattern.proxy.dbroute.db;


public class DynamicDataSourceEntity {

    public final static String DEFAULT_SOURCE = null;

    private final static ThreadLocal<String> LOCAL = new ThreadLocal<String>();

    private DynamicDataSourceEntity() {
    }


    public static String get() {
        return LOCAL.get();
    }

    public static void restore() {
        LOCAL.set(DEFAULT_SOURCE);
    }

    //DB_2018
    //DB_2019
    public static void set(String source) {
        LOCAL.set(source);
    }

    public static void set(int year) {
        LOCAL.set("DB_" + year);
    }

}
