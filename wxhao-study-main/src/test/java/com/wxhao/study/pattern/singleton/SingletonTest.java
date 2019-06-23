package com.wxhao.study.pattern.singleton;

import com.wxhao.study.pattern.singleton.hungry.HungrySingleton;
import com.wxhao.study.pattern.singleton.hungry.HungryStaticSingleton;
import com.wxhao.study.pattern.singleton.lazy.LazyInnerClassSingleton;
import com.wxhao.study.pattern.singleton.lazy.LazySimpleSingleton;
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
 *
 * @author wxhao
 * @date 2019/6/18
 */
public class SingletonTest {

    @Test
    public void testHungrySingleton() {
        //饿汉式单例
        HungrySingleton instance1 = HungrySingleton.getInstance();
        HungrySingleton instance2 = HungrySingleton.getInstance();
        System.out.println(instance1 == instance2);

        //饿汉式静态块单例
        HungryStaticSingleton hungryStaticSingleton1 = HungryStaticSingleton.getInstance();
        HungryStaticSingleton hungryStaticSingleton2 = HungryStaticSingleton.getInstance();
        System.out.println(hungryStaticSingleton1 == hungryStaticSingleton2);
    }

    @Test
    public void testLazySingleton() {
        //简单懒加载单例,会遇到线程问题
        Thread t1 = new Thread(new ExectorThread());
        Thread t2 = new Thread(new ExectorThread());
        t1.start();
        t2.start();
        System.out.println("---分割线---");


    }

    static class ExectorThread implements Runnable {
        @Override
        public void run() {
            LazySimpleSingleton singleton = LazySimpleSingleton.getInstance();
            System.out.println(Thread.currentThread().getName() + ":" + singleton);
        }
    }


    @Test
    public void testLazyInnerClassSingleton() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class<?> clazz = LazyInnerClassSingleton.class;

        //通过反射拿到私有的构造方法
        Constructor c = clazz.getDeclaredConstructor(null);
        //强制访问
        c.setAccessible(true);

        //暴力初始化
        Object o1 = c.newInstance();

        //调用了两次构造方法，相当于new了两次
        Object o2 = c.newInstance();

        System.out.println(o1 == o2);

    }

    @Test
    public void testSeriableSingleton() throws IOException, ClassNotFoundException {
        //序列化破坏单例
        SeriableSingleton s1 = null;
        SeriableSingleton s2 = SeriableSingleton.getInstance();

        s1 = seriableObj(s2, "SeriableSingleton.obj");

        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s1 == s2);

    }

    /**
     * 序列化破坏单例
     *
     * @param s2   待序列化对象
     * @param name 序列化文件名称
     * @param <T>  序列化对象类型
     * @return 反序列化对象
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private <T> T seriableObj(T s2, String name) throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream(name);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(s2);
        oos.flush();
        oos.close();

        FileInputStream fis = new FileInputStream(name);
        ObjectInputStream ois = new ObjectInputStream(fis);
        T s1 = (T) ois.readObject();
        ois.close();
        return s1;
    }

    @Test
    public void testEnumSingleton() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //注册式单例,枚举写法
        EnumSingleton instance1 = null;

        EnumSingleton instance2 = EnumSingleton.getInstance();
        instance2.setData(new Object());

        instance1 = seriableObj(instance2, "EnumSingleton.obj");

        System.out.println(instance1.getData());
        System.out.println(instance2.getData());
        System.out.println(instance1.getData() == instance2.getData());


        //序列化破坏枚举单例,发现不可以,会抛出 java.lang.IllegalArgumentException: Cannot reflectively create enum objects 异常
        Class clazz = EnumSingleton.class;
        Constructor c = clazz.getDeclaredConstructor(String.class, int.class);
        c.setAccessible(true);
        EnumSingleton enumSingleton = (EnumSingleton) c.newInstance("Tom", 666);

    }


    @Test
    public void testContainerSingleton() throws Exception {
        //注册式单例 容器写法
        long start = System.currentTimeMillis();
        //发令枪
        ConcurrentExecutor.execute(() -> {
            Object obj = ContainerSingleton.getInstance("com.wxhao.study.pattern.singleton.Pojo");
            System.out.println(System.currentTimeMillis() + ": " + obj);
        }, 10, 6);
        long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end - start) + " ms.");

    }


    @Test
    public void testThreadLocalSingleton() {

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
