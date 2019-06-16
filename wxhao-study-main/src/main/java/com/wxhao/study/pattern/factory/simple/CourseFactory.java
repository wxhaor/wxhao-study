package com.wxhao.study.pattern.factory.simple;


import com.wxhao.study.pattern.factory.AudiCar;
import com.wxhao.study.pattern.factory.BenzCar;
import com.wxhao.study.pattern.factory.ICar;


public class CourseFactory {

//    public ICar create(String name){
//        if("AudiCar".equals(name)){
//            return new AudiCar();
//        }else if("BenzCar".equals(name)){
//            return new BenzCar();
//        }else {
//            return null;
//        }
//    }

//    public ICar create(String className){
//        try {
//            if (!(null == className || "".equals(className))) {
//                return (ICar) Class.forName(className).newInstance();
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }


    public ICar create(Class<? extends ICar> clazz) {
        //可以限定类
        try {
            if (null != clazz) {
                return clazz.newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
