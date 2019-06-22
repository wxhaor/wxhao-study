package com.wxhao.study.pattern.adapter.poweradapter;

/**
 * 电源适配器 用于把 220V 转为 手机充电器 5V
 * 220V 是早就已经定了的规则,而后来的手机想要充电,就要添加一个适配器
 */
public class PowerAdapter implements Charger {

    private Outlet outlet;

    public PowerAdapter(Outlet outlet) {
        this.outlet = outlet;
    }

    @Override
    public int output5V() {
        //处理适配内容 如:220V->5V
        int adapterInput = outlet.output220V();
        int adapterOutput = adapterInput / 44;
        System.out.println("使用PowerAdapter输入AC:" + adapterInput + "V,输出DC：" + adapterOutput + "V");
        return adapterOutput;
    }
}
