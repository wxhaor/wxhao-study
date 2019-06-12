package com.wxhao.study.proxy.custom;


import com.wxhao.study.proxy.jdk.Animal;
import com.wxhao.study.proxy.jdk.Dog;
import com.wxhao.study.proxy.jdk.Master;

public class TestProxyCustom {


    public static void main(String[] args) {

        try {

            Animal obj = (Animal)new CustomMaster().getInstance(new Dog());
            System.out.println(obj.getClass());
            obj.lachouchou();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
