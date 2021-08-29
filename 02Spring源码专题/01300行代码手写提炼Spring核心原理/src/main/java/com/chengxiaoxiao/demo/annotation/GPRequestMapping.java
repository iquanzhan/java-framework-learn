package com.chengxiaoxiao.demo.annotation;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2021/8/29 0029 18:08
 */
@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface GPRequestMapping {
    String value() default "";
}
