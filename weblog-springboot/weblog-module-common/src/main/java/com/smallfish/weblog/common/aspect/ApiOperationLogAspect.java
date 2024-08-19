package com.smallfish.weblog.common.aspect;

import com.smallfish.weblog.common.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 定义Api请求日志切面
 **/
@Aspect
@Component
@Slf4j
public class ApiOperationLogAspect {

    /**
     * 自定义 @ApiOperationLog 注解为切点，凡是添加 @ApiOperationLog 的方法，都会执行环绕中的代码
     */
    @Pointcut("@annotation(com.smallfish.weblog.common.aspect.ApiOperationLog)")
    public void apiOperationLog() {
    }

    /**
     * 环绕通知
     */
    @Around("apiOperationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            long startTime = System.currentTimeMillis();

            // 每个请求分配一个唯一的标识，并将该标识添加到每条日志消息中，从而方便地区分和跟踪每个请求的日志。
            MDC.put("traceId", UUID.randomUUID().toString());

            // 获取被请求的类和方法
            String className = joinPoint.getTarget().getClass().getSimpleName();
            String methodName = joinPoint.getSignature().getName();

            // 请求入参
            Object[] args = joinPoint.getArgs();

            // 入参转成json字符串
            String argsJsonStr = Arrays.stream(args).map(toJsonStr()).collect(Collectors.joining(","));

            // 功能描述信息
            String description = getApiOperationLogDescription(joinPoint);

            // 打印相关请求
            log.info("=====请求开始:[{}], 入参:{}, 请求类:{}, 请求方法:{} ======================== ",
                    description, argsJsonStr, className, methodName);

            // 执行切点方法
            Object result = joinPoint.proceed();

            // 执行消耗
            long executionTime = System.currentTimeMillis() - startTime;

            // 打印出参等相关信息
            log.info("=====请求结束: [{}], 耗时: {}ms, 出参: {} =================================== ",
                    description, executionTime, JsonUtil.toJsonString(result));
            return result;
        } finally {
            MDC.clear();
        }

    }

    /**
     * 获取注解的描述信息 description里面
     */
    private String getApiOperationLogDescription(ProceedingJoinPoint joinPoint) {

        // 1. 从ProceedingJoinPoint获取MethodSignature
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        // 2. 使用MethodSignature获取当前被注解的Method
        Method method = methodSignature.getMethod();

        // 3. 从Method中提取LogExecution注解
        ApiOperationLog apiOperationLog = method.getAnnotation(ApiOperationLog.class);

        // 4. 从 LogExecution 注解中获取 description 属性
        return apiOperationLog.description();
    }

    private Function<Object, String> toJsonStr() {
        return JsonUtil::toJsonString;
    }
}
