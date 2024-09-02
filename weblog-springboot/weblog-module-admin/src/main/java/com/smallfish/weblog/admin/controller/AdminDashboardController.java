package com.smallfish.weblog.admin.controller;

import com.smallfish.weblog.admin.service.AdminDashboardService;
import com.smallfish.weblog.common.aspect.ApiOperationLog;
import com.smallfish.weblog.common.utils.Result;
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
@RestController
@RequestMapping("/admin/dashboard")
@Api(tags = "Admin 仪表盘")
public class AdminDashboardController {

    @Resource
    private AdminDashboardService adminDashboardService;

    @PostMapping("statistics")
    @ApiOperation(value = "获取后台仪表盘基础统计信息")
    @ApiOperationLog(description = "获取后台仪表盘基础统计信息")
    public Result findDashboardStatisticsInfo() {
        return adminDashboardService.findDashboardStatisticsInfo();
    }

    @PostMapping("publishArticle/statistics")
    @ApiOperation(value = "获取后台仪表盘文章发布热点统计信息")
    @ApiOperationLog(description = "获取后台仪表盘文章发布热点统计信息")
    public Result findDashboardPublishArticleStatistics() {
        return adminDashboardService.findDashboardPublishArticleStatistics();
    }

    @PostMapping("pv/statistics")
    @ApiOperation(value = "获取后台最近一周 pv 统计信息")
    @ApiOperationLog(description = "获取后台pv统计信息获取后台最近一周 pv 统计信息")
    public Result findDashboardPvStatistics() {
        return adminDashboardService.findDashboardPvStatistics();
    }

}
