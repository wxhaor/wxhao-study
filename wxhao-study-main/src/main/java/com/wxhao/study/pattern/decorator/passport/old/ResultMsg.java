package com.wxhao.study.pattern.decorator.passport.old;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultMsg {

    private int code;
    private String msg;
    private Object data;

}
