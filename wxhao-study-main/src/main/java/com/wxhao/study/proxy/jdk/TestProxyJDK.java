package com.wxhao.study.proxy.jdk;


import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

public class TestProxyJDK {

    public static void main(String[] args) {

        try {

            //原理：
            //1.拿到被代理对象的引用，然后获取它的接口
            //2.JDK代理重新生成一个类，同时实现我们给的代理对象所实现的接口
            //3.把被代理对象的引用也拿到了
            //4.重新动态生成一个class字节码
            //5.然后编译

            Animal obj = (Animal) new Master().getInstance(new Dog());
            System.out.println(obj.getClass());
            obj.lachouchou();

            //获取字节码内容
            byte[] data = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Animal.class});
            String filePath = TestProxyJDK.class.getResource("").getPath() + "Proxy0.class";
            //输出class文件,可以反编译查看代理对象
            FileOutputStream os = new FileOutputStream(filePath);
            os.write(data);
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
