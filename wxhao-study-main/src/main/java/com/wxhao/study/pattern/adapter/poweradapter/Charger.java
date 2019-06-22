package com.wxhao.study.pattern.adapter.poweradapter;


/**
 * 定义手机充电器输出,也可以不定义接口
 * 就像可以不规定所有手机充电器都按这个输出
 */
public interface Charger {

    /**
     * 输出5V电压给手机充电
     *
     * @return
     */
    int output5V();

}
