package com.wxhao.study.pattern.adapter.loginadapter.v1.service;

import com.wxhao.study.pattern.adapter.loginadapter.ResultMsg;

/**
 * 不写接口适配器, 因为需求修改,现在要用微信登录,而微信登录只提供openid
 *
 */
public class SinginForThirdService extends SiginService {

    public ResultMsg loginForQQ(String openId) {
        //1、openId是全局唯一，我们可以把它当做是一个用户名(加长)
        //2、密码默认为QQ_EMPTY
        //3、注册（在原有系统里面创建一个用户）

        //4、调用原来的登录方法

        return loginForRegist(openId, null);
    }

    public ResultMsg loginForWechat(String openId) {
        return null;
    }

    public ResultMsg loginForToken(String token) {
        //通过token拿到用户信息，然后再重新登陆了一次
        return null;
    }

    public ResultMsg loginForTelphone(String telphone, String code) {

        return null;
    }

    public ResultMsg loginForRegist(String username, String password) {
        super.regist(username, null);
        return super.login(username, null);
    }
}
