package com.wxhao.study.pattern.factory;

import com.wxhao.study.pattern.factory.abs.AudiCarFactory;
import com.wxhao.study.pattern.factory.method.BenzCarFactory;
import com.wxhao.study.pattern.factory.method.ICarFactory;
import com.wxhao.study.pattern.factory.simple.CourseFactory;
import org.junit.Test;

/**
 * 工厂模式
 *
 * @author wxhao
 * @date 2019/6/16
 */
public class FactoryTest {


    @Test
    public void testSimpleFactory() {

//        ICourse course = new JavaCourse();
//        course.record();

//        ICourseFactory factory = new ICourseFactory();
//        ICourse course = factory.create("com.gupaoedu.vip.pattern.factory.JavaCourse");
//        course.record();

        CourseFactory factory = new CourseFactory();
        ICar course = factory.create(AudiCar.class);
        course.create();

    }

    @Test
    public void testFactoryMethod() {

        ICarFactory factory = new BenzCarFactory();
        ICar course = factory.create();
        course.create();

        factory = new com.wxhao.study.pattern.factory.method.AudiCarFactory();
        course = factory.create();
        course.create();

    }

    @Test
    public void testSbstractFactory() {

        AudiCarFactory factory = new AudiCarFactory();

        factory.createTire().gululu();
        factory.createEngine().hulonglong();

    }


}
