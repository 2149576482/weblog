package com.smallfish.weblog.web.controller;

import com.smallfish.weblog.common.aspect.ApiOperationLog;
import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.service.StatisticsService;
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
@RequestMapping("statistics")
@Api(tags = "统计信息")
public class StatisticsController {

    @Resource
    private StatisticsService statisticsService;
    @PostMapping("/info")
    @ApiOperation(value = "前台获取统计信息")
    @ApiOperationLog(description = "前台获取统计信息")
    public Result findInfo() {

        return statisticsService.findInfo();

    }

}
