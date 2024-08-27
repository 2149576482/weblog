package com.smallfish.weblog.jwt.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smallfish.weblog.common.utils.Result;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
public class ResultUtil {

    /**
     * 成功响应
     */
    public static void ok(HttpServletResponse response, Result result) throws IOException {

        // 设置字符编码
        response.setCharacterEncoding("UTF-8");
        // 响应状态码
        response.setStatus(HttpStatus.OK.value());
        // 设置内容类型
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();

        // 将一个对象转换成JSON格式 写入输出流
        writer.write(new ObjectMapper().writeValueAsString(result));
        writer.flush();
        writer.close();
    }

    /**
     * 失败响应
     */
    public static void fail(HttpServletResponse response, Result result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();

        ObjectMapper objectMapper = new ObjectMapper();
        writer.write(objectMapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }

    /**
     * 失败响应
     */
    public static void fail(HttpServletResponse response, int status, Result result) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();

        ObjectMapper objectMapper = new ObjectMapper();
        writer.write(objectMapper.writeValueAsString(result));
        writer.flush();
        writer.close();
    }

}
