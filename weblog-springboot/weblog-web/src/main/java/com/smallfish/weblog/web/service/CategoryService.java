package com.smallfish.weblog.web.service;

import com.smallfish.weblog.common.utils.Result;

public interface CategoryService {

    /**
     * 获取文章首页分类
     */
    Result findCategoryList();
}
