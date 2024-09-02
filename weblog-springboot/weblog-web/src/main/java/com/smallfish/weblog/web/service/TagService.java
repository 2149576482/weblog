package com.smallfish.weblog.web.service;

import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.model.vo.tag.FindTagArticlePageListReqVO;

public interface TagService {

    /**
     * 获取文章首页标签
     */
    Result findTagList();

    /**
     * 获取首页标签对应文章
     * @return
     */
    Result selectListByTagId(FindTagArticlePageListReqVO findTagArticlePageListReqVO);
}
