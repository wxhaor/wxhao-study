package com.wxhao.study.pattern.prototype;

import com.wxhao.study.pattern.prototype.deep.QiTianDaSheng;
import com.wxhao.study.pattern.prototype.simple.Client;
import com.wxhao.study.pattern.prototype.simple.ConcretePrototypeA;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 原型模式测试
 *
 * @author wxhao
 * @date 2019/6/18
 */
public class PrototypeTest {

    @Test
    public void prototypeTest() {

        // 创建一个具体的需要克隆的对象
        ConcretePrototypeA concretePrototype = new ConcretePrototypeA();
        // 填充属性，方便测试
        concretePrototype.setAge(18);
        concretePrototype.setName("prototype");
        List<String> hobbies = new ArrayList<>();
        concretePrototype.setHobbies(hobbies);
        System.out.println(concretePrototype);

        // 创建Client对象，准备开始克隆
        Client client = new Client(concretePrototype);
        ConcretePrototypeA concretePrototypeClone = (ConcretePrototypeA) client.startClone(concretePrototype);
        System.out.println(concretePrototypeClone);

        System.out.println("克隆对象中的引用类型地址值：" + concretePrototypeClone.getHobbies());
        System.out.println("原对象中的引用类型地址值：" + concretePrototype.getHobbies());
        System.out.println("对象地址比较：" + (concretePrototypeClone.getHobbies() == concretePrototype.getHobbies()));

    }

    @Test
    public void deepCloneTest() {
        QiTianDaSheng qiTianDaSheng = new QiTianDaSheng();
        try {
            QiTianDaSheng clone = (QiTianDaSheng)qiTianDaSheng.clone();
            System.out.println("深克隆：" + (qiTianDaSheng.jinGuBang == clone.jinGuBang));
        } catch (Exception e) {
            e.printStackTrace();
        }

        QiTianDaSheng q = new QiTianDaSheng();
        QiTianDaSheng n = q.shallowClone(q);
        System.out.println("浅克隆：" + (q.jinGuBang == n.jinGuBang));
    }

}
