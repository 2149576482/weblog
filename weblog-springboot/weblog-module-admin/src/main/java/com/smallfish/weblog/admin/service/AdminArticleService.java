package com.smallfish.weblog.admin.service;

import com.smallfish.weblog.admin.model.vo.article.*;
import com.smallfish.weblog.common.utils.Result;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
public interface AdminArticleService {

    /**
     * 发布文章
     */
    Result publishArticle(PublishArticleReqVO publishArticleReqVO);

    /**
     * 删除标签
     */
    Result deleteArticle(DeleteArticleReqVO deleteArticleReqVO);

    /**
     * 分页查询文章
     */
    Result findArticleList(FindArticlePageListReqVO findArticlePageListReqVO);

    /**
     * 查询文章详情
     */
    Result findCategorySelectList(FindArticleDetailReqVO findArticleDetailReqVO);

    /**
     * 更新文章
     */
    Result updateArticle(UpdateArticleReqVO updateArticleReqVO);
}
