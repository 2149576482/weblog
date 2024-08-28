package com.smallfish.weblog.web.controller;

import com.smallfish.weblog.common.aspect.ApiOperationLog;
import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.service.CategoryService;
import com.smallfish.weblog.web.service.TagService;
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
@RequestMapping("category")
@RestController
@Api(tags = "分类")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/list")
    @ApiOperation(value = "获取首页文章分类数据")
    @ApiOperationLog(description = "获取首页文章分类数据")
    public Result findCategoryList() {
        return categoryService.findCategoryList();
    }

}
