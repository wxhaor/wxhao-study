package com.wxhao.study.pattern.prototype.simple;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Client {

    private Prototype prototype;

    public Prototype startClone(Prototype concretePrototype){
        return concretePrototype.clone();
    }

}
