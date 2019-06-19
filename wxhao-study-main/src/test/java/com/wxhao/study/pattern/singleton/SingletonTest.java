package com.wxhao.study.pattern.singleton;

import com.wxhao.study.pattern.singleton.lazy.LazyInnerClassSingleton;
import com.wxhao.study.pattern.singleton.register.ContainerSingleton;
import com.wxhao.study.pattern.singleton.register.EnumSingleton;
import com.wxhao.study.pattern.singleton.seriable.SeriableSingleton;
import com.wxhao.study.pattern.singleton.threadlocal.ThreadLocalSingleton;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 单例模式测试
 * @author wxhao
 * @date 2019/6/18
 */
public class SingletonTest {

    @Test
    public void lazySimpleSingletonTest() {
        Thread t1 = new Thread(new ExectorThread());
        Thread t2 = new Thread(new ExectorThread());
        t1.start();
        t2.start();
        System.out.println("End");
    }

    @Test
    public void lazyInnerClassSingletonTest() {
        try {
            //很无聊的情况下，进行破坏
            Class<?> clazz = LazyInnerClassSingleton.class;

            //通过反射拿到私有的构造方法
            Constructor c = clazz.getDeclaredConstructor(null);
            //强制访问，强吻，不愿意也要吻
            c.setAccessible(true);

            //暴力初始化
            Object o1 = c.newInstance();

            //调用了两次构造方法，相当于new了两次
            //犯了原则性问题，
            Object o2 = c.newInstance();

            System.out.println(o1 == o2);
//            Object o2 = c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void containerSingletonTest() {

        try {
            long start = System.currentTimeMillis();
            ConcurrentExecutor.execute(new ConcurrentExecutor.RunHandler() {
                public void handler() {
                    Object obj = ContainerSingleton.getInstance("com.wxhao.study.pattern.singleton.Pojo");
                    ;
                    System.out.println(System.currentTimeMillis() + ": " + obj);
                }
            }, 10, 6);
            long end = System.currentTimeMillis();
            System.out.println("总耗时：" + (end - start) + " ms.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void enumSingletonTest() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        EnumSingleton instance1 = null;

        EnumSingleton instance2 = EnumSingleton.getInstance();
        instance2.setData(new Object());

        FileOutputStream fos = new FileOutputStream("EnumSingleton.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(instance2);
        oos.flush();
        oos.close();

        FileInputStream fis = new FileInputStream("EnumSingleton.obj");
        ObjectInputStream ois = new ObjectInputStream(fis);
        instance1 = (EnumSingleton) ois.readObject();
        ois.close();

        System.out.println(instance1.getData());
        System.out.println(instance2.getData());
        System.out.println(instance1.getData() == instance2.getData());

        Class clazz = EnumSingleton.class;
        Constructor c = clazz.getDeclaredConstructor(String.class, int.class);
        c.setAccessible(true);
        EnumSingleton enumSingleton = (EnumSingleton) c.newInstance("Tom", 666);

    }


    @Test
    public void seriableSingletonTest() {

        SeriableSingleton s1 = null;
        SeriableSingleton s2 = SeriableSingleton.getInstance();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream("SeriableSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);
            oos.flush();
            oos.close();


            FileInputStream fis = new FileInputStream("SeriableSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            s1 = (SeriableSingleton) ois.readObject();
            ois.close();

            System.out.println(s1);
            System.out.println(s2);
            System.out.println(s1 == s2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void threadLocalSingletonTest() {

        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());
        System.out.println(ThreadLocalSingleton.getInstance());

        Thread t1 = new Thread(new ExectorThread());
        Thread t2 = new Thread(new ExectorThread());
        t1.start();
        t2.start();
        System.out.println("End");
    }
}
