package com.wxhao.study.pattern.adapter;

import com.wxhao.study.pattern.adapter.loginadapter.v1.service.SinginForThirdService;
import com.wxhao.study.pattern.adapter.loginadapter.v2.IPassportForThird;
import com.wxhao.study.pattern.adapter.loginadapter.v2.PassportForThirdAdapter;
import com.wxhao.study.pattern.adapter.poweradapter.Outlet;
import com.wxhao.study.pattern.adapter.poweradapter.Charger;
import com.wxhao.study.pattern.adapter.poweradapter.PowerAdapter;
import org.junit.Test;

/**
 * 适配器模式
 *
 * @author wxhao
 * @date 2019/6/20
 */
public class AdapterTest {



    @Test
    public void testPowerAdapter() {
        //获取手机充电,把两个本不兼容的东西,兼容起来
        Charger charger = new PowerAdapter(new Outlet());
        charger.output5V();
    }

    @Test
    public void testPassport() {

        IPassportForThird passportForThird = new PassportForThirdAdapter();

        passportForThird.loginForQQ("");

    }



    @Test
    public void testSiginForThirdService() {
        SinginForThirdService service = new SinginForThirdService();
        service.login("tom", "123456");
        service.loginForQQ("sdfasdfasf");
        service.loginForWechat("sdfasfsa");
    }


}
