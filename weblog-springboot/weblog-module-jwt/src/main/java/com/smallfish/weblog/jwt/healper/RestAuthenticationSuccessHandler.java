package com.smallfish.weblog.jwt.healper;

import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.jwt.model.LoginRspVO;
import com.smallfish.weblog.jwt.utils.JwtTokenHelper;
import com.smallfish.weblog.jwt.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 自定义认证成功处理器 登陆成功
 **/
@Component
@Slf4j
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtTokenHelper jwtTokenHelper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // 从 authentication对象获取用户的UserDetails实例这里获取用户的用户名
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 通过用户名 生成token
        String username = userDetails.getUsername();
        String token = jwtTokenHelper.generateToken(username);

        // 返回token
        LoginRspVO loginRspVO = LoginRspVO.builder().token(token).build();

        ResultUtil.ok(response, Result.ok(loginRspVO));

    }
}
