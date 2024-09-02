package com.smallfish.weblog.web.service;

import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.model.vo.article.FindArticleDetailReqVO;
import com.smallfish.weblog.web.model.vo.article.FindIndexArticlePageListReqVO;

public interface ArticleService {

    /**
     * 分页查询所有文章
     */
    Result findArticlePageList(FindIndexArticlePageListReqVO findIndexArticlePageListReqVO);

    /**
     * 获取文章详情
     */
    Result findDetail(FindArticleDetailReqVO findArticleDetailReqVO);
}
