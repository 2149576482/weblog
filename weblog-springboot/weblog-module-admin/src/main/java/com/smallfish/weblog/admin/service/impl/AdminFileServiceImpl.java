package com.smallfish.weblog.admin.service.impl;

import com.smallfish.weblog.admin.model.vo.file.UploadFileRspVO;
import com.smallfish.weblog.admin.service.AdminFileService;
import com.smallfish.weblog.admin.utils.MinioUtil;
import com.smallfish.weblog.common.enums.ResultCodeEnum;
import com.smallfish.weblog.common.exception.BusinessException;
import com.smallfish.weblog.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Service
@Slf4j
public class AdminFileServiceImpl implements AdminFileService {

    @Resource
    private MinioUtil minioUtil;

    /**
     * 上传文件
     */
    @Override
    public Result uploadFile(MultipartFile file) {
        try {
            // 上传文件
            String url = minioUtil.uploadFile(file);

            // 构建成功返参 将图片链接返回
            return Result.ok(UploadFileRspVO.builder().url(url).build());
        } catch (Exception e) {
            log.error("==> 上传文件至Minio错误: ", e);
            throw new BusinessException(ResultCodeEnum.FILE_UPLOAD_FAILED);
        }
    }
}
