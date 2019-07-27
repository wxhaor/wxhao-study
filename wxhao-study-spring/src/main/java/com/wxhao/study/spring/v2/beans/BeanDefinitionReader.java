package com.wxhao.study.spring.v2.beans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 读取配置文件
 *
 * @author wxhao
 * @date 2019/7/27
 */
public class BeanDefinitionReader {

    /**
     * 包下所有类名
     */
    private List<String> registyBeanClasses = new ArrayList<String>();
    private Properties config = new Properties();
    //固定配置文件中的 key，相对于 xml 的规范
    private final String SCAN_PACKAGE = "scanPackage";

    public BeanDefinitionReader(String... configLoactions) {
        //通过 URL 定位找到其所对应的文件，然后转换为文件流
        InputStream is =
                this.getClass().getClassLoader().getResourceAsStream(configLoactions[0].replace("classpath:", ""));
        try {
            config.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        doScanner(config.getProperty(SCAN_PACKAGE));

    }

    /**
     * 根据包路径扫描类
     *
     * @param scanPackage
     */
    private void doScanner(String scanPackage) {
//转换为文件路径，实际上就是把.替换为/就 OK 了
        URL url = this.getClass().getClassLoader().getResource("/" +
                scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")) {
                    continue;
                }
                String className = (scanPackage + "." + file.getName().replace(".class", ""));
                registyBeanClasses.add(className);
            }
        }
    }

    public Properties getConfig() {
        return this.config;
    }

    public List<BeanDefinition> loadBeanDefinitions() {

        List<BeanDefinition> result = new ArrayList<>();
        try {
            for (String className : registyBeanClasses) {
                Class<?> beanClass = Class.forName(className);
                if (beanClass.isInterface()) {
                    continue;
                }
                result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()), beanClass.getName(
                )));
                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> i : interfaces) {
                    result.add(doCreateBeanDefinition(i.getName(), beanClass.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //把每一个配信息解析成一个 BeanDefinition
    private BeanDefinition doCreateBeanDefinition(String factoryBeanName, String beanClassName) {
        BeanDefinition beanDefinition = new BeanDefinition();

        beanDefinition.setBeanClassName(beanClassName);
        beanDefinition.setFactoryBeanName(factoryBeanName);
        return beanDefinition;
    }

    //如果类名本身是小写字母，确实会出问题
//但是我要说明的是：这个方法是我自己用，private 的
//传值也是自己传，类也都遵循了驼峰命名法
//默认传入的值，存在首字母小写的情况，也不可能出现非字母的情况
//为了简化程序逻辑，就不做其他判断了，大家了解就 OK
//其实用写注释的时间都能够把逻辑写完了
    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
//之所以加，是因为大小写字母的 ASCII 码相差 32，
// 而且大写字母的 ASCII 码要小于小写字母的 ASCII 码
//在 Java 中，对 char 做算学运算，实际上就是对 ASCII 码做算学运算
        chars[0] += 32;
        return String.valueOf(chars);
    }

}
