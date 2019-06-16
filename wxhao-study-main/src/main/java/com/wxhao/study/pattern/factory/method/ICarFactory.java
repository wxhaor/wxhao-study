package com.wxhao.study.pattern.factory.method;

import com.wxhao.study.pattern.factory.ICar;

/**
 * 工厂模型
 * 造车工厂
 */
public interface ICarFactory {

    /**
     * 造车
     * @return
     */
    ICar create();

}
