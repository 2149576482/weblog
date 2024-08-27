package com.smallfish.weblog.admin.utils;

import com.smallfish.weblog.admin.config.MinioProperties;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Slf4j
@Component
public class MinioUtil {

    @Resource
    private MinioClient minioClient;

    @Resource
    private MinioProperties minioProperties;

    /**
     * 上传文件
     */
    public String uploadFile(MultipartFile file) throws Exception {
        // 判断是否为空
        if (file == null || file.getSize() == 0) {
            log.error("==> 上传文件异常，文件大小为空...");
            throw new RuntimeException("文件大小不能为空");
        }

        // 文件的原始名字
        String originalFilename = file.getOriginalFilename();
        // 文件的类型 content-Type
        String contentType = file.getContentType();

        // 生成存储对象的名称 (将UUID字符串中的 - 替换成空字符串)
        String key = UUID.randomUUID().toString().replace("-", "");
        // 获取文件的后缀名
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));

        // 拼接上文件后缀 即存储的文件名
        String objectName = String.format("%s%s", key, substring);

        log.info("==> 开始上传文件至 Minio, objectName: {}", objectName);

        // 上传文件至Minio
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(minioProperties.getBucketName())
                .object(objectName)
                .stream(file.getInputStream(), file.getSize(), -1)
                .contentType(contentType)
                .build());

        // 返回文件的访问链接
        String format = String.format("%s/%s/%s", minioProperties.getEndpoint(), minioProperties.getBucketName(), objectName);
        log.info("==> 文件上传至Minio成功, 访问路径: {}", format);
        return format;
    }

}
