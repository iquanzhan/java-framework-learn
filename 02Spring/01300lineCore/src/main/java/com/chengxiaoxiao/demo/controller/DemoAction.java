package com.chengxiaoxiao.demo.controller;

import com.chengxiaoxiao.demo.annotation.GPAutowired;
import com.chengxiaoxiao.demo.annotation.GPController;
import com.chengxiaoxiao.demo.annotation.GPRequestMapping;
import com.chengxiaoxiao.demo.annotation.GPRequestParam;
import com.chengxiaoxiao.demo.service.IDemoService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2021/8/29 0029 18:13
 */
@GPController
@GPRequestMapping("/demo")
public class DemoAction {
    @GPAutowired
    private IDemoService iDemoService;

    @GPRequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response, @GPRequestParam("name") String name) {
        String s = iDemoService.get(name);
        try {
            response.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GPRequestMapping("/add")
    public void add(HttpServletRequest request, HttpServletResponse response, @GPRequestParam("a") Integer a, @GPRequestParam("b") Integer b) {
        try {
            response.getWriter().write(a + "+" + b + "=" + (a + b));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
