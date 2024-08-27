package com.smallfish.weblog.admin.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 客户端配置类
 **/
@Configuration
public class MinioConfig {

    @Resource
    private MinioProperties minioProperties;

    /**
     * 客户端配置
     */
    @Bean
    public MinioClient minioClient() {
        return MinioClient
                .builder()
                .endpoint(minioProperties.getEndpoint())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
    }
}
