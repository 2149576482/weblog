package com.smallfish.weblog.web.controller;

import com.smallfish.weblog.common.aspect.ApiOperationLog;
import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.model.vo.archive.FindIndexArchivePageListReqVO;
import com.smallfish.weblog.web.model.vo.article.FindArticleDetailReqVO;
import com.smallfish.weblog.web.service.ArchiveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("archive")
@Api(tags = "归档")
public class ArchiveController {

    @Resource
    private ArchiveService archiveService;

    @PostMapping("list")
    @ApiOperation(value = "获取归档数据")
    @ApiOperationLog(description = "获取归档数据")
    public Result findArchiveList(@RequestBody FindIndexArchivePageListReqVO findIndexArchivePageListReqVO) {
        return archiveService.findArchiveList(findIndexArchivePageListReqVO);
    }
}
