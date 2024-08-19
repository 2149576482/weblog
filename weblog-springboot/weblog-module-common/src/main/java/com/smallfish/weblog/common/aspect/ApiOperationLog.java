package com.smallfish.weblog.common.aspect;

import java.lang.annotation.*;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 定义日志注解
 **/
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiOperationLog {

    /**
     * 功能描述
     */
    String description() default "";

}
