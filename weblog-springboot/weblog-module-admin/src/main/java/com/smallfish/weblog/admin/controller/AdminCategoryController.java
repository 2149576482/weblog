package com.smallfish.weblog.admin.controller;

import com.smallfish.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.smallfish.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.smallfish.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.smallfish.weblog.admin.service.AdminCategoryService;
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
@RequestMapping("/admin")
@Api(tags = "Admin 分类模块")
public class AdminCategoryController {

    @Resource
    private AdminCategoryService adminCategoryService;

    /**
     * 进行分类添加
     */
    @PostMapping("category/add")
    @ApiOperationLog(description = "添加分类")
    @ApiOperation(value = "添加分类")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result addCategory(@RequestBody @Validated AddCategoryReqVO addCategoryReqVO) {
        return adminCategoryService.addCategory(addCategoryReqVO);
    }

    /**
     * 进行分页查询
     */
    @PostMapping("category/list")
    @ApiOperationLog(description = "分类分页获取数据")
    @ApiOperation(value = "分类分页获取数据")
    public Result findCategoryList(@RequestBody FindCategoryPageListReqVO findCategoryPageListReqVO) {
        return adminCategoryService.findCategoryList(findCategoryPageListReqVO);
    }

    /**
     * 删除分类
     */
    @PostMapping("category/delete")
    @ApiOperationLog(description = "删除分类")
    @ApiOperation(value = "删除分类")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Result deleteCategory(@RequestBody DeleteCategoryReqVO deleteCategoryReqVO) {
        return adminCategoryService.deleteCategory(deleteCategoryReqVO);
    }

    /**
     * 查询分类
     */
    @PostMapping("category/select/list")
    @ApiOperationLog(description = "分类 Select 下拉列表数据获取")
    @ApiOperation(value = "分类 Select 下拉列表数据获取")
    public Result findCategorySelectList() {
        return adminCategoryService.findCategorySelectList();
    }

}
