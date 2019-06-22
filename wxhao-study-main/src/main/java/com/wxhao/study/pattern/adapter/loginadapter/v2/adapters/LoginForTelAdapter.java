package com.wxhao.study.pattern.adapter.loginadapter.v2.adapters;

import com.wxhao.study.pattern.adapter.loginadapter.ResultMsg;

public class LoginForTelAdapter implements LoginAdapter {
    @Override
    public boolean support(Object adapter) {
        return adapter instanceof LoginForTelAdapter;
    }

    @Override
    public ResultMsg login(String id, Object adapter) {
        return null;
    }
}
