package com.wxhao.study.pattern.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BenzCar implements ICar {

    //创建一个汽车需要很多零件

    private String gulu;

    private String dadeng;

    @Override
    public void create() {
        System.out.println("奔驰,轱辘:" + gulu + ",大灯:" + dadeng);
    }

}
