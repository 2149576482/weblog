package com.smallfish.weblog.jwt.healper;

import com.smallfish.weblog.common.enums.ResultCodeEnum;
import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.jwt.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 登录成功访问收保护的资源，但是权限不够
 **/
@Slf4j
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn("登录成功访问收保护的资源，但是权限不够: ", accessDeniedException);
        // 预留，后面引入多角色时会用到
        ResultUtil.fail(response, Result.fail(ResultCodeEnum.FORBIDDEN));
    }

}
