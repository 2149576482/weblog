package com.smallfish.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.smallfish.weblog.common.domain.dos.ArticleContentDO;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
public interface ArticleContentMapper extends BaseMapper<ArticleContentDO> {

    /**
     * 根据文章ID删除记录
     */
    default int deleteByArticleId(Long articleId) {
        return delete(Wrappers.<ArticleContentDO>lambdaQuery()
                .eq(ArticleContentDO::getArticleId, articleId));
    }

    /**
     * 根据文章id 查询正文
     */
    default String findContentByArticleId(Long articleId) {
        ArticleContentDO articleContentDO = selectOne(Wrappers.<ArticleContentDO>lambdaQuery()
                .eq(ArticleContentDO::getArticleId, articleId));
        return articleContentDO.getContent();
    }

    /**
     * 根据文章id 更新正文
     */
    default int updateContentByArticleId(ArticleContentDO articleContentDO) {
        return update(articleContentDO, Wrappers.<ArticleContentDO>lambdaUpdate()
                .eq(ArticleContentDO::getArticleId, articleContentDO.getArticleId()));
    }

}
