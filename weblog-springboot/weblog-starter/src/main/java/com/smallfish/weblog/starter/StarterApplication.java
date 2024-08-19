package com.smallfish.weblog.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ArnanZZ
 * @version 1.0
 **/
@SpringBootApplication
@ComponentScan(basePackages = "com.smallfish.weblog")
public class StarterApplication {
    public static void main(String[] args) {
        SpringApplication.run(StarterApplication.class, args);
    }
}
