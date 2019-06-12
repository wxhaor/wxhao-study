package com.wxhao.study.proxy;

import com.wxhao.study.proxy.cglib.Cat;
import com.wxhao.study.proxy.cglib.CglibMaster;
import com.wxhao.study.proxy.custom.CustomMaster;
import com.wxhao.study.proxy.jdk.Animal;
import com.wxhao.study.proxy.jdk.Dog;
import com.wxhao.study.proxy.jdk.Master;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * 动态代理 代码入口
 * @author wxhao
 * @date 2019/6/12
 */

public class ProxyTest {


    @Test
    public void testProxyCglib() {

        //JDK的动态代理是通过接口来进行强制转换的
        //生成以后的代理对象，可以强制转换为接口


        //CGLib的动态代理是通过生成一个被代理对象的子类，然后重写父类的方法
        //生成以后的对象，可以强制转换为被代理对象（也就是用自己写的类）
        //子类引用赋值给父类

        try {
            Cat obj = (Cat) new CglibMaster().getInstance(Cat.class);
            obj.lachouchou();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProxyJDK() {

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
            String filePath = Master.class.getResource("").getPath() + "Proxy0.class";
            //输出class文件,可以反编译查看代理对象
            FileOutputStream os = new FileOutputStream(filePath);
            os.write(data);
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testProxyCustom() {
        try {

            Animal obj = (Animal) new CustomMaster().getInstance(new Dog());
            System.out.println(obj.getClass());
            obj.lachouchou();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
