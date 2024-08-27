package com.smallfish.weblog.admin.controller;

import com.smallfish.weblog.admin.service.AdminFileService;
import com.smallfish.weblog.common.aspect.ApiOperationLog;
import com.smallfish.weblog.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 文件上传controller
 **/
@RestController
@RequestMapping("/admin")
@Api(tags = "Admin 文件模块")
public class AdminFileController {

    @Resource
    private AdminFileService adminFileService;

    @PostMapping("file/upload")
    @ApiOperation(value = "文件上传")
    @ApiOperationLog(description = "文件上传")
    public Result uploadFile(@RequestParam MultipartFile file) {
        return adminFileService.uploadFile(file);
    }

}
