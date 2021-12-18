package com.chengxiaoxiao.demo.annotation;

import java.lang.annotation.*;

/**
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2021/8/29 0029 18:06
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface GPController {
    String value() default "";
}
