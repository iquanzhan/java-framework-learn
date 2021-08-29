package com.chengxiaoxiao.demo.servlet.v1;

import com.chengxiaoxiao.demo.annotation.GPAutowired;
import com.chengxiaoxiao.demo.annotation.GPController;
import com.chengxiaoxiao.demo.annotation.GPRequestMapping;
import com.chengxiaoxiao.demo.annotation.GPService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * 核心处理类
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2021/8/29 0029 17:56
 */
public class GPDispatcherServlet extends HttpServlet {

    private Map<String, Object> mapping = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception " + Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * 分配 执行请求
     *
     * @param req
     * @param resp
     */
    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath, "").replaceAll("/+", "/");

        if (!mapping.containsKey(url)) {
            resp.getWriter().write("404 Not Found");
            return;
        }
        Method method = (Method) this.mapping.get(url);
        Map<String, String[]> parameterMap = req.getParameterMap();
        method.invoke(this.mapping.get(method.getDeclaringClass().getName()), new Object[]{req, resp, parameterMap.get("name")[0]});

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        InputStream inputStream = null;
        try {
            Properties properties = new Properties();
            inputStream = this.getClass().getClassLoader().getResourceAsStream(config.getInitParameter("contextConfigLocation"));
            properties.load(inputStream);

            String scanPackage = properties.getProperty("scanPackage");
            //扫描包
            doScanner(scanPackage);
            //解析所有扫描到的包，并进行实例化
            for (String className : mapping.keySet()) {
                if (!className.contains(".")) {
                    continue;
                }
                Class<?> clazz = Class.forName(className);
                if (clazz.isInterface()) {
                    continue;
                }
                Object instance = clazz.newInstance();
                if (clazz.isAnnotationPresent(GPController.class)) {
                    mapping.put(className, instance);
                    String baseUrl = "";
                    if (clazz.isAnnotationPresent(GPRequestMapping.class)) {
                        GPRequestMapping requestMapping = clazz.getAnnotation(GPRequestMapping.class);
                        baseUrl = requestMapping.value();
                    }
                    for (Method method : clazz.getMethods()) {
                        if (!method.isAnnotationPresent(GPRequestMapping.class)) {
                            continue;
                        }
                        GPRequestMapping requestMapping = method.getAnnotation(GPRequestMapping.class);
                        String url = (baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
                        mapping.put(url, method);
                    }
                } else if (clazz.isAnnotationPresent(GPService.class)) {
                    GPService gpService = clazz.getAnnotation(GPService.class);
                    String beanName = gpService.value();
                    if ("".equals(beanName)) {
                        beanName = clazz.getName();
                    }
                    mapping.put(beanName, instance);
                    for (Class<?> i : clazz.getInterfaces()) {
                        mapping.put(i.getName(), instance);
                    }
                }
            }

            //进行依赖注入
            for (Object object : mapping.values()) {
                if (object == null) {
                    continue;
                }
                Class<?> clazz = object.getClass();
                if (clazz.isAnnotationPresent(GPController.class)) {
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        if (!field.isAnnotationPresent(GPAutowired.class)) {
                            continue;
                        }
                        GPAutowired autowired = field.getAnnotation(GPAutowired.class);
                        String beanName = autowired.value();
                        if ("".equals(beanName)) {
                            beanName = field.getType().getName();
                        }
                        field.setAccessible(true);
                        field.set(mapping.get(clazz.getName()), mapping.get(beanName));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 扫描包
     *
     * @param scanPackage 父级包
     */
    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        assert url != null;
        File classDir = new File(url.getFile());

        for (File file : Objects.requireNonNull(classDir.listFiles())) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
                continue;
            }
            if (!file.getName().endsWith(".class")) {
                continue;
            }
            String clazzName = scanPackage + "." + file.getName().replace(".class", "");
            mapping.put(clazzName, null);
        }

    }
}
