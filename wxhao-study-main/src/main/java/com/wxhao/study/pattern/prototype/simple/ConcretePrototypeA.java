package com.wxhao.study.pattern.prototype.simple;

import lombok.Data;

import java.util.List;

@Data
public class ConcretePrototypeA implements Prototype {

    private int age;
    private String name;
    private List<String> hobbies;

    @Override
    public ConcretePrototypeA clone() {
        ConcretePrototypeA concretePrototype = new ConcretePrototypeA();
        concretePrototype.setAge(this.age);
        concretePrototype.setName(this.name);
        concretePrototype.setHobbies(this.hobbies);
        return concretePrototype;
    }

}
