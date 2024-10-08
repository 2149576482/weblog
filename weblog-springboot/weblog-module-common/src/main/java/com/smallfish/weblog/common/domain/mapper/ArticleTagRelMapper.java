package com.smallfish.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.smallfish.weblog.common.config.InsertBatchMapper;
import com.smallfish.weblog.common.domain.dos.ArticleDO;
import com.smallfish.weblog.common.domain.dos.ArticleTagRelDO;

import java.util.List;
import java.util.stream.Collectors;

public interface ArticleTagRelMapper extends InsertBatchMapper<ArticleTagRelDO> {

    default int deleteByArticleId(Long articleId) {
        return delete(Wrappers.<ArticleTagRelDO>lambdaQuery()
                .eq(ArticleTagRelDO::getArticleId, articleId));
    }

    /**
     * 根据文章 ID 查询标签
     */
    default List<Long> findTagByArticleId(Long articleId) {
        List<ArticleTagRelDO> articleTagRelDOS = selectList(Wrappers.<ArticleTagRelDO>lambdaQuery()
                .eq(ArticleTagRelDO::getArticleId, articleId));
        return articleTagRelDOS.stream().map(ArticleTagRelDO::getTagId).collect(Collectors.toList());
    }

    /**
     * 根据标签id 查询有无记录
     */
    default ArticleTagRelDO selectOneByTagId(Long tagId) {
        return selectOne(Wrappers.<ArticleTagRelDO>lambdaQuery()
                .eq(ArticleTagRelDO::getTagId, tagId)
                .last("LIMIT 1"));

    }

    /**
     * 根据文章id 查询所有的标签
     */
    default List<ArticleTagRelDO> selectByArticleIds(List<Long> articleIds) {
        return selectList(Wrappers.<ArticleTagRelDO>lambdaQuery()
                .in(ArticleTagRelDO::getArticleId, articleIds));
    }

    /**
     * 根据标签id 获取到所有的文章
     */
    default List<ArticleTagRelDO> selectListByTagId(Long tagId) {
        return selectList(Wrappers.<ArticleTagRelDO>lambdaQuery()
                .eq(ArticleTagRelDO::getTagId, tagId));
    }
}