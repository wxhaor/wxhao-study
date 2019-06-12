package com.wxhao.study.proxy;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;

/**
 * @author wxhao
 * @date 2019/6/8
 */
public class MProxy {

    public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)
            throws IllegalArgumentException {
        try {

            //1. 生成class源码
            String classStr = "public class $Proxy0 {}";

            //2. 输出到磁盘保存为java文件

            String filePath = MProxy.class.getResource("").getPath();
            File file = new File(filePath + "$Proxy0.java");

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(classStr);
            fileWriter.flush();
            fileWriter.close();


            //3. 编译源码,生成class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);

            Iterable<? extends JavaFileObject> javaFileObjects = manager.getJavaFileObjects(file);

            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, null, null, javaFileObjects);
            task.call();
            manager.close();


            //4. 加载class文件,动态加载到jvm

            // 读文件 ClassLoader.defineClass , 类加载器找到被夹在类返回Class对象 ,通过Constructor 获取对象


            //5. 返回被代理后的对象



        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
