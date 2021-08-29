package com.chengxiaoxiao.demo.service.impl;

import com.chengxiaoxiao.demo.service.IDemoService;

/**
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2021/8/29 0029 18:12
 */
public class DemoService implements IDemoService {
    @Override
    public String get(String name) {
        return "My Name is " + name;
    }
}
