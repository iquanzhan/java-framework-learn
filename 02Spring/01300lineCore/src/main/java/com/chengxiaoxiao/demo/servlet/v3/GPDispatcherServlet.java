package com.chengxiaoxiao.demo.servlet.v3;

import cn.hutool.core.util.StrUtil;
import com.chengxiaoxiao.demo.annotation.*;
import lombok.Data;
import lombok.SneakyThrows;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * v2版本
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2021/8/31 0031 20:46
 */
public class GPDispatcherServlet extends HttpServlet {
    //保存配置文件中的内容
    private Properties contextConfig = new Properties();
    //保存所有的类名信息
    private List<String> classNames = new ArrayList<>();
    //IOC容器
    private Map<String, Object> ioc = new HashMap<>();
    //保存url和method的对应关系
    private List<Handler> handlerMapping = new ArrayList<>();


    /**
     * Handler记录Controller中的requestMapping和Method的对应关系
     */
    @Data
    private class Handler {
        protected Object controller;
        protected Method method;
        protected Pattern pattern;
        protected Map<String, Integer> parameterIndexMapping;//参数的顺序

        public Handler(Object controller, Method method, Pattern pattern) {
            this.controller = controller;
            this.method = method;
            this.pattern = pattern;
            parameterIndexMapping = new HashMap<>();
            putParamIndexMapping(method);
        }

        private void putParamIndexMapping(Method method) {
            Annotation[][] pa = method.getParameterAnnotations();
            for (int i = 0; i < pa.length; i++) {
                for (Annotation annotation : pa[i]) {
                    if (annotation instanceof GPRequestParam) {
                        String value = ((GPRequestParam) annotation).value();
                        if (!"".equals(value.trim())) {
                            parameterIndexMapping.put(value, i);
                        }
                    }
                }
            }
            //提取方法中的request和response
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                Class<?> parameterType = parameterTypes[i];
                if (parameterType == HttpServletResponse.class || parameterType == HttpServletRequest.class) {
                    parameterIndexMapping.put(parameterType.getName(), i);
                }
            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
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
     * @param req
     * @param resp
     */
    @SneakyThrows
    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        Handler handler = getHandler(req);
        if (handler == null) {
            resp.getWriter().write("404 Not Found");
            return;
        }
        Map<String, String[]> parameterMap = req.getParameterMap();

        Class<?>[] parameterTypes = handler.getMethod().getParameterTypes();
        Object[] paramValues = new Object[parameterTypes.length];
        for (Map.Entry<String, String[]> stringEntry : parameterMap.entrySet()) {
            if (!handler.parameterIndexMapping.containsKey(stringEntry.getKey())) {
                continue;
            }
            Integer index = handler.parameterIndexMapping.get(stringEntry.getKey());
            paramValues[index] = convert(parameterTypes[index], Arrays.toString(stringEntry.getValue()));
        }
        if (handler.parameterIndexMapping.containsKey(HttpServletRequest.class.getName())) {
            int reqIndex = handler.parameterIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
        }
        if (handler.parameterIndexMapping.containsKey(HttpServletResponse.class.getName())) {
            int respIndex = handler.parameterIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;
        }

        Object returnValue = handler.method.invoke(handler.controller, paramValues);
        if (returnValue == null || returnValue instanceof Void) {
            return;
        }
        resp.getWriter().write(returnValue.toString());
    }

    private Object convert(Class<?> type, String value) {
        if (Integer.class == type) {
            return Integer.valueOf(value);
        }
        return value;
    }

    private Handler getHandler(HttpServletRequest req) {
        if (handlerMapping.isEmpty()) {
            return null;
        }
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        for (Handler handler : handlerMapping) {
            Matcher matcher = handler.pattern.matcher(url);
            if (matcher.matches()) {
                return handler;
            }
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //1.加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        //2.扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));

        //3.初始化扫描到的类，并将他们放到IOC容器中
        doInstance();

        //4.完成依赖注入
        doAutowired();

        //5.初始化HandlerMapping
        initHandlerMapping();

        System.out.println("GPDispatcher is init");
    }

    //加载handlerMapping
    private void initHandlerMapping() {
        if (ioc.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (clazz.isAnnotationPresent(GPController.class)) {
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
                    String regex = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
                    Pattern pattern = Pattern.compile(regex);
                    handlerMapping.add(new Handler(entry.getValue(), method, pattern));
                }
            }
        }

    }

    //依赖注入
    @SneakyThrows
    private void doAutowired() {
        if (ioc.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            for (Field declaredField : entry.getValue().getClass().getDeclaredFields()) {
                if (!declaredField.isAnnotationPresent(GPAutowired.class)) {
                    continue;
                }
                GPAutowired autowired = declaredField.getAnnotation(GPAutowired.class);
                String beanName = autowired.value();
                if ("".equals(beanName)) {
                    beanName = declaredField.getType().getName();
                }
                declaredField.setAccessible(true);
                declaredField.set(entry.getValue(), ioc.get(beanName));
            }
        }
    }

    //扫描相关的类
    @SneakyThrows
    private void doInstance() {
        if (classNames.isEmpty()) {
            return;
        }

        for (String className : classNames) {
            Class<?> clazz = Class.forName(className);
            if (clazz.isInterface()) {
                continue;
            }
            Object instance = clazz.newInstance();
            if (clazz.isAnnotationPresent(GPController.class)) {
                ioc.put(StrUtil.lowerFirst(clazz.getSimpleName()), instance);
            } else if (clazz.isAnnotationPresent(GPService.class)) {
                GPService gpService = clazz.getAnnotation(GPService.class);
                String beanName = gpService.value();
                if ("".equals(beanName)) {
                    beanName = clazz.getName();
                }
                ioc.put(beanName, instance);
                for (Class<?> i : clazz.getInterfaces()) {
                    ioc.put(i.getName(), instance);
                }
            }
        }

    }

    //扫描
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
            classNames.add(clazzName);
        }
    }

    /**
     * 加载配置文件
     *
     * @param contextConfigLocation
     */
    private void doLoadConfig(String contextConfigLocation) {
        InputStream inputStream = null;
        try {
            Properties properties = new Properties();
            inputStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
            properties.load(inputStream);
        } catch (IOException e) {
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
}
