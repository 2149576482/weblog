package com.smallfish.weblog.common.utils;

import com.smallfish.weblog.common.exception.BaseExceptionInterface;
import com.smallfish.weblog.common.exception.BusinessException;
import lombok.Data;

import javax.xml.ws.Response;
import java.io.Serializable;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 统一响应工具
 **/
@Data
public class Result<T> implements Serializable {

    // 成功与否
    private Boolean success = true;

    // 响应消息
    private String message;

    // 状态码
    private String ErrorCode;

    // 数据
    private T data;

    // =================================== 成功响应 ===================================
    public static <T> Result<T> ok() {
        return new Result<>();
    }

    public static <T> Result<T> ok(T data) {
        Result<T> response = new Result<>();
        response.setData(data);
        return response;
    }

    // =================================== 失败响应 ===================================
    public static <T> Result<T> fail() {
        Result<T> response = new Result<>();
        response.setSuccess(false);
        return response;
    }

    public static <T> Result<T> fail(String errorMessage) {
        Result<T> response = new Result<>();
        response.setSuccess(false);
        response.setMessage(errorMessage);
        return response;
    }

    public static <T> Result<T> fail(String errorCode, String errorMessage) {
        Result<T> response = new Result<>();
        response.setSuccess(false);
        response.setErrorCode(errorCode);
        response.setMessage(errorMessage);
        return response;
    }

    public static <T> Result<T> fail(BusinessException bizException) {
        Result<T> response = new Result<>();
        response.setSuccess(false);
        response.setErrorCode(bizException.getErrorCode());
        response.setMessage(bizException.getErrorMessage());
        return response;
    }

    public static <T> Result<T> fail(BaseExceptionInterface baseExceptionInterface) {
        Result<T> response = new Result<>();
        response.setSuccess(false);
        response.setErrorCode(baseExceptionInterface.getErrorCode());
        response.setMessage(baseExceptionInterface.getErrorMessage());
        return response;
    }


}
