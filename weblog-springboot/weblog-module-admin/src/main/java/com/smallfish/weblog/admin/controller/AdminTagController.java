package com.smallfish.weblog.admin.controller;

import com.smallfish.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.smallfish.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.smallfish.weblog.admin.model.vo.tag.*;
import com.smallfish.weblog.admin.service.AdminTagService;
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
 * @description: 标签controller
 **/
@RestController
@RequestMapping("admin")
@Api(tags = "Admin 标签模块")
public class AdminTagController {

    @Resource
    private AdminTagService adminTagService;

    /**
     * 进行标签添加
     */
    @PostMapping("tag/add")
    @ApiOperation(value = "添加标签")
    @ApiOperationLog(description = "添加标签")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result addTag(@RequestBody @Validated AddTagReqVO addTagReqVO) {
        return adminTagService.addTag(addTagReqVO);
    }

    /**
     * 进行分页查询标签
     */
    @PostMapping("tag/list")
    @ApiOperationLog(description = "标签分页获取数据")
    @ApiOperation(value = "标签分页获取数据")
    public Result findTagList(@RequestBody FindTagPageListReqVO findTagPageListReqVO) {
        return adminTagService.findTagList(findTagPageListReqVO);
    }

    /**
     * 删除标签
     */
    @PostMapping("tag/delete")
    @ApiOperationLog(description = "删除标签")
    @ApiOperation(value = "删除标签")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result deleteTag(@RequestBody DeleteTagReqVO deleteTagReqVO) {
        return adminTagService.deleteTag(deleteTagReqVO);
    }

    /**
     * 查询标签
     */
    @PostMapping("tag/search")
    @ApiOperationLog(description = "标签 列表模糊查询 下拉列表数据获取")
    @ApiOperation(value = "标签 列表模糊查询 下拉列表数据获取")
    public Result findTagSelectList(@RequestBody @Validated SearchTagReqVO searchTagReqVO) {
        return adminTagService.findTagSelectList(searchTagReqVO);
    }

    /**
     * 查询下拉 select 标签
     */
    @PostMapping("tag/select/list")
    @ApiOperationLog(description = "获取所有select下拉列表")
    @ApiOperation(value = "获取所有select下拉列表")
    public Result findTagSearch() {
        return adminTagService.findTagSearch();
    }
}
