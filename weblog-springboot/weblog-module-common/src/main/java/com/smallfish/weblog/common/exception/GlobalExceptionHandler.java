package com.smallfish.weblog.common.exception;

import com.smallfish.weblog.common.enums.ResultCodeEnum;
import com.smallfish.weblog.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 全局异常处理
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获自定义业务异常
     */
    @ExceptionHandler({ BusinessException.class })
    @ResponseBody
    public Result<Object> handleBusinessException(HttpServletRequest request, BusinessException e) {
        log.warn("{} request fail, errorCode: {}, errorMessage: {}", request.getRequestURI(), e.getErrorCode(), e.getErrorMessage());
        return Result.fail(e);
    }

    /**
     * 捕获参数校验异常
     */
    @ExceptionHandler({ MethodArgumentNotValidException.class })
    @ResponseBody
    public Result<Object> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {

        // 参数错误异常码
        String errorCode = ResultCodeEnum.PARAM_NOT_VALID.getErrorCode();

        // 获取BindingResult
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder stringBuilder = new StringBuilder();

        // 获取FieldError 如果错误执行
        Optional.ofNullable(bindingResult.getFieldErrors()).ifPresent(errors -> {
            errors.forEach(error ->
                    stringBuilder.append(error.getField())
                            .append(" ")
                            .append(error.getDefaultMessage())
                            .append(",当前值: '")
                            .append(error.getRejectedValue())
                            .append("';"));
        });

        String errorMessage = stringBuilder.toString();
        log.warn("{} request fail, errorCode: {}, errorMessage: {}", request.getRequestURI(), errorCode, errorMessage);
        return Result.fail(errorCode, errorMessage);
    }

    @ExceptionHandler({ AccessDeniedException.class })
    public void throwAccessDeniedException(AccessDeniedException e) throws AccessDeniedException {
        // 捕捉到鉴权失败异常 主动抛出 交给RestAccessDeniedHandler 处理
        log.info("============= 捕获到 AccessDeniedException");
        throw e;
    }

    /**
     * 捕获其他异常
     */
    @ExceptionHandler({ Exception.class })
    @ResponseBody
    public Result<Object> handlerOtherException(HttpServletRequest request, Exception e) {
        log.error("{} request error, ", request.getRequestURI(), e);
        return Result.fail(ResultCodeEnum.SYSTEM_ERROR);
    }


}
