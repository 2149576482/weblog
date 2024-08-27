package com.smallfish.weblog.admin.controller;

import com.smallfish.weblog.admin.model.vo.article.*;
import com.smallfish.weblog.admin.service.AdminArticleService;
import com.smallfish.weblog.common.aspect.ApiOperationLog;
import com.smallfish.weblog.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("admin/article")
@Api(tags = "Admin 文章模块")
public class AdminArticleController {

    @Resource
    private AdminArticleService articleService;

    /**
     * 发布文章
     */
    @PostMapping("publish")
    @ApiOperation(value = "发布文章")
    @ApiOperationLog(description = "发布文章")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result publishArticle(@RequestBody PublishArticleReqVO publishArticleReqVO) {
        return articleService.publishArticle(publishArticleReqVO);
    }

    /**
     * 删除文章
     */
    @PostMapping("delete")
    @ApiOperation(value = "删除文章")
    @ApiOperationLog(description = "删除文章")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result deleteArticle(@RequestBody DeleteArticleReqVO deleteArticleReqVO) {
        return articleService.deleteArticle(deleteArticleReqVO);
    }

    /**
     * 分页获取文章数据
     */
    @PostMapping("list")
    @ApiOperationLog(description = "文章分页获取数据")
    @ApiOperation(value = "文章分页获取数据")
    public Result findArticleList(@RequestBody FindArticlePageListReqVO findArticlePageListReqVO) {
        return articleService.findArticleList(findArticlePageListReqVO);
    }

    /**
     * 获取文章详情
     */
    @PostMapping("detail")
    @ApiOperationLog(description = "查询文章详情")
    @ApiOperation(value = "查询文章详情")
    public Result findCategorySelectList(@RequestBody @Validated FindArticleDetailReqVO findArticleDetailReqVO) {
        return articleService.findCategorySelectList(findArticleDetailReqVO);
    }

    /**
     * 更新文章
     */
    @PostMapping("update")
    @ApiOperationLog(description = "更新文章")
    @ApiOperation(value = "更新文章")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result updateArticle(@RequestBody @Validated UpdateArticleReqVO updateArticleReqVO) {
        return articleService.updateArticle(updateArticleReqVO);
    }


}























