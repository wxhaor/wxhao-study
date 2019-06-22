package com.wxhao.study.pattern.strategy;

import com.wxhao.study.pattern.strategy.pay.Order;
import com.wxhao.study.pattern.strategy.pay.payport.PayStrategy;
import com.wxhao.study.pattern.strategy.promotion.CashbackStrategy;
import com.wxhao.study.pattern.strategy.promotion.CouponStrategy;
import com.wxhao.study.pattern.strategy.promotion.PromotionActivity;
import org.junit.Test;

/**
 * 策略模式
 * @author wxhao
 * @date 2019/6/20
 */
public class StrategyTest {


    @Test
    public void TestPromotionActivity() {
        //促销活动
        PromotionActivity activity618 = new PromotionActivity(new CouponStrategy());
        PromotionActivity activity1111 = new PromotionActivity(new CashbackStrategy());

        activity618.execute();
        activity1111.execute();

//        PromotionActivity promotionActivity = null;
//
//        String promotionKey = "COUPON";
//
//        if(StringUtils.equals(promotionKey,"COUPON")){
//            promotionActivity = new PromotionActivity(new CouponStrategy());
//        }else if(StringUtils.equals(promotionKey,"CASHBACK")){
//            promotionActivity = new PromotionActivity(new CashbackStrategy());
//        }//......
//        promotionActivity.execute();


//        String promotionKey = "GROUPBUY";
//        PromotionActivity promotionActivity = new PromotionActivity(PromotionStrategyFactory.getPromotionStrategy(promotionKey));
//        promotionActivity.execute();
    }

    @Test
    public void testPayStrategy() {

        //省略把商品添加到购物车，再从购物车下单
        //直接从点单开始
        Order order = new Order("1", "20180311001000009", 324.45);

        //开始支付，选择微信支付、支付宝、银联卡、京东白条、财付通
        //每个渠道它支付的具体算法是不一样的
        //基本算法固定的

        //这个值是在支付的时候才决定用哪个值
        System.out.println(order.pay(PayStrategy.ALI_PAY));


    }

}
