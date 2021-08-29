package com.chengxiaoxiao.demo.annotation;

import java.lang.annotation.*;

/**
 *
 * @Description:
 * @Author: Cheng XiaoXiao
 * @Date: 2021/8/29 0029 17:57
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GPService {
    String value() default "";
}
