package com.wxhao.study.spring.core;

import com.alibaba.fastjson.JSONObject;
import com.wxhao.study.spring.core.annotation.Autowire;
import com.wxhao.study.spring.core.annotation.Controller;
import com.wxhao.study.spring.core.annotation.RequestMapping;
import com.wxhao.study.spring.core.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wxhao
 * @date 2019/6/27
 */

public class DispatcherServlet extends HttpServlet {

    private Properties myConfig = new Properties();
    private ClassLoader classLoader = this.getClass().getClassLoader();
    private List<String> tobeLoadClass = new ArrayList<>();
    private Map<String, Object> ioc = new ConcurrentHashMap<>();


    @Override
    public void init(ServletConfig config) throws ServletException {

        String configName = config.getInitParameter("configName");

        //1. 读取配置文件
        loadConfig(configName);

        //2. 扫描待加载类
        scanningClass(myConfig.getProperty("scanPackage").replaceAll("\\.", "/"));
        System.out.println("--tobeLoadClass:" + JSONObject.toJSONString(tobeLoadClass));

        //3. 加载类 到IOC容器

        loadClass();
        System.out.println("--ioc:" + JSONObject.toJSONString(ioc));

        //4. DI and HandlerMapping
        doDI();
        System.out.println("--handlerMappingMap:" + JSONObject.toJSONString(handlerMappingMap));


    }

    private Map<String, Method> handlerMappingMap = new ConcurrentHashMap<>();

    private void doDI() {
        for (String s : tobeLoadClass) {
            try {
                String fullName = s.replaceAll("/", "\\.").substring(0, s.lastIndexOf("."));
                Class<?> clazz = classLoader.loadClass(fullName);

                if (!clazz.isAnnotationPresent(Controller.class)) {
                    continue;
                }
                StringBuilder sb = new StringBuilder("/");
                if (clazz.isAnnotationPresent(RequestMapping.class)) {
                    sb.append(clazz.getAnnotation(RequestMapping.class).value());
                }

                Object obj = clazz.newInstance();

                Field[] declaredFields = clazz.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    if (declaredField.isAnnotationPresent(Autowire.class)) {
                        declaredField.setAccessible(true);
                        declaredField.set(obj, ioc.get(declaredField.getType().getName()));
                    }
                }

                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(RequestMapping.class)) {
                        sb.append("/");
                        sb.append(method.getAnnotation(RequestMapping.class).value());
                        handlerMappingMap.put(sb.toString().replaceAll("/+", "/"), method);
                    }
                }


                ioc.put(fullName, obj);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        }
    }

    private void loadClass() {
        tobeLoadClass.forEach(s -> {
            try {
                String fullName = s.replaceAll("/", "\\.").substring(0, s.lastIndexOf("."));
                Class<?> clazz = classLoader.loadClass(fullName);

                if (clazz.isAnnotationPresent(Service.class)) {
                    ioc.put(fullName, clazz.newInstance());
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        });
    }


    private void scanningClass(String scanPackage) {
        URL resource = classLoader.getResource(scanPackage);
        File parentFile = new File(resource.getFile());
        for (File file : parentFile.listFiles()) {
            String fileName = file.getName();
            String filePath = scanPackage + "/" + fileName;
            if (file.isDirectory()) {
                scanningClass(filePath);
            } else {
                if (fileName.endsWith(".class")) {
                    tobeLoadClass.add(filePath);
                }
            }
        }
    }

    private void loadConfig(String configName) {
        try {
            myConfig.load(classLoader.getResourceAsStream(configName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();

        String url = ("/" + requestURI.substring(requestURI.indexOf(contextPath) + contextPath.length())).replaceAll("/+", "/");
        Method method = handlerMappingMap.get(url);
        if (method == null) {
            resp.getWriter().println("404");
        }

        try {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Object[] args = new Object[parameterTypes.length];
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> parameterType = parameterTypes[i];
                if (parameterType == HttpServletRequest.class) {
                    args[i] = req;
                } else if (parameterType == HttpServletResponse.class) {
                    args[i] = resp;
                }
            }

            Object obj = ioc.get(method.getDeclaringClass().getName());
            Object invoke ;
            if (args.length == 0) {
                invoke = method.invoke(obj);
            } else {
                invoke = method.invoke(obj, args);
            }
            resp.getWriter().println(invoke);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }


}
