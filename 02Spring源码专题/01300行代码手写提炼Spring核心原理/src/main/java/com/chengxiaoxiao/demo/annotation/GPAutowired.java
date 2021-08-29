package com.chengxiaoxiao.demo.annotation;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2021/8/29 0029 18:04
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface GPAutowired {
    String value() default "";
}
