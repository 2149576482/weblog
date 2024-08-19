package com.smallfish.weblog.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: knife4j配置
 **/
@Profile("dev")
@Configuration
@EnableSwagger2WebMvc
public class Knife4jWebConfig {

    @Bean("webApi")
    public Docket createApiDoc() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInfo())
                // 分组名称
                .groupName("web 前台接口")
                .select()
                // 这里指定controller扫描
                .apis(RequestHandlerSelectors.basePackage("com.smallfish.weblog.web.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    /**
     * 构建api信息
     */
    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
                .title("weblog 博客 web 前台接口文档")
                .description("Weblog 是一款由 Spring Boot + Vue 3.2 + Vite 4.3 开发的前后端分离博客。")
                .termsOfServiceUrl("https://www.smallfish.com/")
                .contact(new Contact("小鱼","https://www.smallfish.com", "871361652@qq.com"))
                .version("1.0")
                .build();
    }

}
