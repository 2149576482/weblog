package com.smallfish.weblog.jwt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 自定义异常 处理AuthenticationException
 **/
public class UsernameOrPasswordNullException extends AuthenticationException {

    public UsernameOrPasswordNullException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UsernameOrPasswordNullException(String msg) {
        super(msg);
    }
}

