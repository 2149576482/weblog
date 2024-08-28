package com.smallfish.weblog.web.controller;

import com.smallfish.weblog.common.aspect.ApiOperationLog;
import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.service.BlogSettingsService;
import com.smallfish.weblog.web.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@RequestMapping("blog/settings")
@RestController
@Api(tags = "博客个人设置")
public class BlogSettingsController {

    @Resource
    private BlogSettingsService blogSettingsService;

    @PostMapping("detail")
    @ApiOperation(value = "获取首页个人博客数据")
    @ApiOperationLog(description = "获取首页个人博客数据")
    public Result findDetail() {
        return blogSettingsService.findDetail();
    }


}
