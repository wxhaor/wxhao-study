package com.wxhao.study.spring.v2.context;

import com.wxhao.study.spring.core.annotation.Autowired;
import com.wxhao.study.spring.core.annotation.Controller;
import com.wxhao.study.spring.core.annotation.Service;
import com.wxhao.study.spring.v2.beans.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wxhao
 * @date 2019/7/14
 */
public class ApplicationContext extends DefaultListableBeanFactory implements BeanFactory {

    private String[] configLoactions;

    private BeanDefinitionReader reader;

    //用来保证注册式单例的容器
    private Map<String, Object> singletonBeanCacheMap = new HashMap<String, Object>();
    //用来存储所有的被代理过的对象
    private Map<String, BeanWrapper> beanWrapperMap = new ConcurrentHashMap<>();
    /**
     * 单例的 IOC 容器缓存
     */
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();
    /**
     * 通用的 IOC 容器
     */
    private Map<String, BeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();

    public ApplicationContext(String... configLoactions) {
        this.configLoactions = configLoactions;
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh() throws Exception {

        //1、定位，定位配置文件
        reader = new BeanDefinitionReader(this.configLoactions);

        //2、加载配置文件，扫描相关的类，把它们封装成 BeanDefinition
        List<BeanDefinition> beanDefinitions = reader.loadBeanDefinitions();

        //3、注册，把配置信息放到容器里面(伪 IOC 容器)
        doRegisterBeanDefinition(beanDefinitions);
        //4、把不是延时加载的类，有提前初始化
        doAutowrited();

    }

    private void doRegisterBeanDefinition(List<BeanDefinition> beanDefinitions) throws Exception {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            if (super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())) {
                throw new Exception("The “" + beanDefinition.getFactoryBeanName() + "” is exists!!");
            }
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(), beanDefinition);
        }
        //到这里为止，容器初始化完毕
    }


    //只处理非延时加载的情况
    private void doAutowrited() {
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry :
                super.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            if (!beanDefinitionEntry.getValue().isLazyInit()) {
                try {
                    getBean(beanName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public Object getBean(Class<?> beanClass) throws Exception {
        return getBean(beanClass.getName());
    }

    //依赖注入，从这里开始，通过读取 BeanDefinition 中的信息
    //然后，通过反射机制创建一个实例并返回
    //Spring 做法是，不会把最原始的对象放出去，会用一个 BeanWrapper 来进行一次包装
    //装饰器模式：
    //1、保留原来的 OOP 关系
    //2、我需要对它进行扩展，增强（为了以后 AOP 打基础）
    @Override
    public Object getBean(String beanName) throws Exception {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        try {
//生成通知事件
            BeanPostProcessor beanPostProcessor = new BeanPostProcessor();
            Object instance = instantiateBean(beanDefinition);
            if (null == instance) {
                return null;
            }
            //在实例初始化以前调用一次
            beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
            BeanWrapper beanWrapper = new BeanWrapper(instance);
            this.beanWrapperMap.put(beanName, beanWrapper);
            //在实例初始化以后调用一次
            beanPostProcessor.postProcessAfterInitialization(instance, beanName);
            populateBean(beanName, instance);
          //通过这样一调用，相当于给我们自己留有了可操作的空间
            return this.beanWrapperMap.get(beanName).getWrappedInstance();
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }

    //传一个 BeanDefinition，就返回一个实例 Bean
    private Object instantiateBean(BeanDefinition beanDefinition) {
        Object instance = null;
        String className = beanDefinition.getBeanClassName();
        try {
//因为根据 Class 才能确定一个类是否有实例
            if (this.singletonBeanCacheMap.containsKey(className)) {
                instance = this.singletonBeanCacheMap.get(className);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.singletonBeanCacheMap.put(beanDefinition.getFactoryBeanName(), instance);
            }
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void populateBean(String beanName, Object instance) {
        Class clazz = instance.getClass();

        if (!(clazz.isAnnotationPresent(Controller.class) ||
                clazz.isAnnotationPresent(Service.class))) {
            return;
        }
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(Autowired.class)) {
                continue;
            }
            Autowired autowired = field.getAnnotation(Autowired.class);
            String autowiredBeanName = autowired.value().trim();
            if ("".equals(autowiredBeanName)) {
                autowiredBeanName = field.getType().getName();
            }
            field.setAccessible(true);
            try {
                field.set(instance, this.beanWrapperMap.get(autowiredBeanName).getWrappedInstance());
            } catch (IllegalAccessException e) {
                // e.printStackTrace();
            }
        }
    }



    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new
                String[this.beanDefinitionMap.size()]);
    }
    public int getBeanDefinitionCount(){
        return this.beanDefinitionMap.size();
    }
    public Properties getConfig(){
        return this.reader.getConfig();
    }

}
