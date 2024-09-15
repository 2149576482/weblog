package com.smallfish.weblog.admin.controller;

import com.smallfish.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.smallfish.weblog.admin.service.AdminBlogSettingService;
import com.smallfish.weblog.common.aspect.ApiOperationLog;
import com.smallfish.weblog.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@RestController
@RequestMapping("/admin/blog/settings")
@Api(tags = "Admin 博客设置模块")
public class AdminBlogSettingsController {

    @Resource
    private AdminBlogSettingService adminBlogSettingService;

    @PostMapping("update")
    @ApiOperation(value = "博客基础信息修改")
    @ApiOperationLog(description = "博客基础信息修改")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result updateBlogSettings(@RequestBody UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        return adminBlogSettingService.updateBlogSettings(updateBlogSettingsReqVO);
    }

    @PostMapping("detail")
    @ApiOperation(value = "博客基础信息获取")
    @ApiOperationLog(description = "博客基础信息获取")
    public Result findDetail() {
        return adminBlogSettingService.findDetail();
    }

}
