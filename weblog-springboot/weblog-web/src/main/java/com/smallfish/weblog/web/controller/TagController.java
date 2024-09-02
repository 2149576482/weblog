package com.smallfish.weblog.web.controller;

import com.smallfish.weblog.common.aspect.ApiOperationLog;
import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.model.vo.tag.FindTagArticlePageListReqVO;
import com.smallfish.weblog.web.service.CategoryService;
import com.smallfish.weblog.web.service.TagService;
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
@RequestMapping("tag")
@RestController
@Api(tags = "标签")
public class TagController {

    @Resource
    private TagService tagService;

    @PostMapping("/list")
    @ApiOperation(value = "获取首页文章标签数据")
    @ApiOperationLog(description = "获取首页文章标签数据")
    public Result findArticleList() {
        return tagService.findTagList();
    }

    @PostMapping("/article/list")
    @ApiOperation(value = "获取首页标签对应文章")
    @ApiOperationLog(description = "获取首页标签对应文章")
    public Result selectListByTagId(@RequestBody FindTagArticlePageListReqVO findTagArticlePageListReqVO) {
        return tagService.selectListByTagId(findTagArticlePageListReqVO);
    }

}
