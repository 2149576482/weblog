package com.smallfish.weblog.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 启动类
 **/
@SpringBootApplication
@EnableScheduling // 启用定时
@ComponentScan(basePackages = "com.smallfish.weblog")
@MapperScan(basePackages = "com.smallfish.**.mapper")
public class StarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarterApplication.class, args);
    }
}
