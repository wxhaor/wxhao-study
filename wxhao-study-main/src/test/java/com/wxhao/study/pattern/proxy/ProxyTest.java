package com.wxhao.study.pattern.proxy;

import com.wxhao.study.pattern.proxy.dbroute.IOrderService;
import com.wxhao.study.pattern.proxy.dbroute.Order;
import com.wxhao.study.pattern.proxy.dbroute.OrderService;
import com.wxhao.study.pattern.proxy.dbroute.proxy.OrderServiceDynamicProxy;
import com.wxhao.study.pattern.proxy.dynamicproxy.cglibproxy.CglibMeipo;
import com.wxhao.study.pattern.proxy.dynamicproxy.cglibproxy.Customer;
import com.wxhao.study.pattern.proxy.dynamicproxy.customproxy.MyMeipo;
import com.wxhao.study.pattern.proxy.dynamicproxy.jdkproxy.Girl;
import com.wxhao.study.pattern.proxy.dynamicproxy.jdkproxy.JDKMeipo;
import com.wxhao.study.pattern.proxy.simpleproxy.Proxy;
import com.wxhao.study.pattern.proxy.simpleproxy.RealSubject;
import com.wxhao.study.pattern.proxy.staticproxy.Father;
import com.wxhao.study.pattern.proxy.staticproxy.Son;
import net.sf.cglib.core.DebuggingClassWriter;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 代理模式
 *
 * @author wxhao
 * @date 2019/6/18
 */
public class ProxyTest {

    @Test
    public void testSimpleProxy() {
        //代理示意
        Proxy proxy = new Proxy(new RealSubject());
        proxy.request();

    }

    @Test
    public void testStaticProxy() {
        //静态代理
        Father father = new Father(new Son());
        father.findLove();


        //大家每天都在用的一种静态代理的形式
        //对数据库进行分库分表
        //ThreadLocal
        //进行数据源动态切换
    }

    @Test
    public void testJDKProxy() {
        //JDK动态代理
        try {

            Object obj = new JDKMeipo().getInstance(new Girl());
            Method method = obj.getClass().getMethod("findLove", null);
            method.invoke(obj);

            //obj.findLove();

            //原理: 动态生成字节码
            //$Proxy0
            byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
            FileOutputStream os = new FileOutputStream(this.getClass().getResource("").getPath() + "/$Proxy0.class");
            os.write(bytes);
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCglib() {
        try {

            //JDK是采用读取接口的信息
            //CGLib覆盖父类方法
            //目的：都是生成一个新的类，去实现增强代码逻辑的功能

            //JDK Proxy 对于用户而言，必须要有一个接口实现，目标类相对来说复杂
            //CGLib 可以代理任意一个普通的类，没有任何要求

            //CGLib 生成代理逻辑更复杂，效率,调用效率更高，生成一个包含了所有的逻辑的FastClass，不再需要反射调用
            //JDK Proxy生成代理的逻辑简单，执行效率相对要低，每次都要反射动态调用

            //CGLib 有个坑，CGLib不能代理final的方法

            //设置debug类输出到
            System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, this.getClass().getResource("").getPath());

            Customer obj = (Customer) new CglibMeipo().getInstance(Customer.class);
            System.out.println(obj);
            obj.findLove();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCustomProxy() {
        try {

            //JDK动态代理的实现原理
            Person obj = (Person) new MyMeipo().getInstance(new Girl());
            System.out.println(obj.getClass());
            obj.findLove();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDbRouteProxy() {
        try {
            Order order = new Order();

//            order.setCreateTime(new Date().getTime());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date date = sdf.parse("2017/02/01");
            order.setCreateTime(date.getTime());

            IOrderService orderService = (IOrderService) new OrderServiceDynamicProxy().getInstance(new OrderService());
            orderService.createOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
