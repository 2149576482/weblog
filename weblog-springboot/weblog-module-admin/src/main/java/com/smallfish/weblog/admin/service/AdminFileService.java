package com.smallfish.weblog.admin.service;

import com.smallfish.weblog.common.utils.Result;
import org.springframework.web.multipart.MultipartFile;

public interface AdminFileService {

    /**
     * 上传文件
     */
    Result uploadFile(MultipartFile file);

}
