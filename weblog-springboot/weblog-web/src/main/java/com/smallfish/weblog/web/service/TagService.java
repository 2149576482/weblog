package com.smallfish.weblog.web.service;

import com.smallfish.weblog.common.utils.Result;

public interface TagService {

    /**
     * 获取文章首页标签
     */
    Result findTagList();
}
