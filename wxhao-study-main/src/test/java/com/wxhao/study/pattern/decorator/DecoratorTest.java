package com.wxhao.study.pattern.decorator;

import com.wxhao.study.pattern.decorator.battercake.v1.Battercake;
import com.wxhao.study.pattern.decorator.battercake.v1.BattercakeWithEgg;
import com.wxhao.study.pattern.decorator.battercake.v1.BattercakeWithEggAndSausage;
import com.wxhao.study.pattern.decorator.battercake.v2.BaseBattercake;
import com.wxhao.study.pattern.decorator.battercake.v2.EggDecorator;
import com.wxhao.study.pattern.decorator.battercake.v2.SausageDecorator;
import com.wxhao.study.pattern.decorator.passport.old.SigninService;
import com.wxhao.study.pattern.decorator.passport.upgrade.ISiginForThirdService;
import com.wxhao.study.pattern.decorator.passport.upgrade.SiginForThirdService;
import org.junit.Test;

/**
 * 装饰者模式
 *
 * @author wxhao
 * @date 2019/6/20
 */
public class DecoratorTest {
    @Test
    public void testBattercake() {
        Battercake battercake = new Battercake();
        System.out.println(battercake.getMsg() + ",总价格：" + battercake.getPrice());

        Battercake battercakeWithEgg = new BattercakeWithEgg();
        System.out.println(battercakeWithEgg.getMsg() + ",总价格：" + battercakeWithEgg.getPrice());

        Battercake battercakeWithEggAndSausage = new BattercakeWithEggAndSausage();
        System.out.println(battercakeWithEggAndSausage.getMsg() + ",总价格：" + battercakeWithEggAndSausage.getPrice());


    }

    @Test
    public void testBattercake2() {
        com.wxhao.study.pattern.decorator.battercake.v2.Battercake battercake;
        //路边摊买一个煎饼
        battercake = new BaseBattercake();
        //煎饼有点小，想再加一个鸡蛋
        battercake = new EggDecorator(battercake);
        //再加一个鸡蛋
//        battercake = new EggDecorator(battercake);
        //很饿，再加根香肠
        battercake = new SausageDecorator(battercake);
        battercake = new SausageDecorator(battercake);
        battercake = new SausageDecorator(battercake);
        battercake = new SausageDecorator(battercake);
        battercake = new SausageDecorator(battercake);


        //跟静态代理最大区别就是职责不同
        //静态代理不一定要满足is-a的关系
        //静态代理会做功能增强，同一个职责变得不一样

        //装饰器更多考虑是扩展

        System.out.println(battercake.getMsg() + ",总价：" + battercake.getPrice());
    }

    @Test
    public void testDecorator() {

        //满足一个is-a
        ISiginForThirdService siginForThirdService = new SiginForThirdService(new SigninService());
        siginForThirdService.loginForQQ("sdfasfdasfsf");

    }

}
