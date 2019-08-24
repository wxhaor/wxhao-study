package com.wxhao.study.filetool;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

/**
 * @author wxhao
 * @date 2019/8/13
 */
@Getter
@AllArgsConstructor
public enum FileMappingEnum {

    X_1("课后作业", Collections.singletonList("课后作业")),
    X_2("课堂源码", Collections.singletonList("课堂源码")),
    //X_3("录播视频",Arrays.asList(""));
    X_3("上课课件", Collections.singletonList("上课课件")),
    X_4("预习资料", Collections.singletonList("预习资料")),

    ;

    private String prefix;

    private List<String> mapping;

}
