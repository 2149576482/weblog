package com.smallfish.weblog.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 自定义业务异常
 **/
@Getter
@Setter
public class BusinessException extends RuntimeException {

    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

    /**
     * 定义构造方法 传入一个接口 可以是枚举
     */
    public BusinessException(BaseExceptionInterface baseExceptionInterface) {
        this.errorCode = baseExceptionInterface.getErrorCode();
        this.errorMessage = baseExceptionInterface.getErrorMessage();
    }

}
