package com.example.demo.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: flyboy
 * @Date: 22/03/2021 15:12
 * @Version 1.0
 * @Email: 1308794149@qq.com
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    /**
     * 限流次数
     * @return
     */
    int seconds() default 10;
    int limit() default 10;
    boolean needLogin() default true;
}