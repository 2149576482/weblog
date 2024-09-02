package com.smallfish.weblog.web.controller;

import com.smallfish.weblog.common.aspect.ApiOperationLog;
import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.model.vo.search.SearchArticlePageListReqVO;
import com.smallfish.weblog.web.service.SearchService;
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
@RequestMapping("article")
@Api(tags = "搜索")
public class SearchController {

    @Resource
    private SearchService searchService;

    @PostMapping("search")
    @ApiOperation(value = "搜索")
    @ApiOperationLog(description = "搜索")
    public Result searchArticlePageList(@RequestBody SearchArticlePageListReqVO searchArticlePageListReqVO) {
        return searchService.searchArticlePageList(searchArticlePageListReqVO);
    }
}
