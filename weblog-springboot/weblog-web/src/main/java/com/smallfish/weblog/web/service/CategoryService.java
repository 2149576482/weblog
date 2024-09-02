package com.smallfish.weblog.web.service;

import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.model.vo.category.FindCategoryArticlePageListReqVO;

public interface CategoryService {

    /**
     * 获取文章首页分类
     */
    Result findCategoryList();

    /**
     * 获取分类对应文章
     */
    Result selectListByCategoryId(FindCategoryArticlePageListReqVO findCategoryArticlePageListReqVO);
}
