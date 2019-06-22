package com.wxhao.study.pattern.adapter.loginadapter;

import lombok.Data;

@Data
public class ResultMsg {

    private int code;
    private String msg;
    private Object data;

    public ResultMsg(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
