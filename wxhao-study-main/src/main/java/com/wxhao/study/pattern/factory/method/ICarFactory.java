package com.wxhao.study.pattern.factory.method;

import com.wxhao.study.pattern.factory.ICar;

/**
 * 工厂方法 造车工厂
 */
public interface ICarFactory {

    /**
     * 提供材料
     * @return
     */
    ICar provide();

}
