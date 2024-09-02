package com.smallfish.weblog.common.domain.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.smallfish.weblog.common.domain.dos.ArticleCategoryRelDO;

import java.util.List;

public interface ArticleCategoryRelMapper extends BaseMapper<ArticleCategoryRelDO> {

    default int deleteByArticleId(Long articleId) {
        return delete(Wrappers.<ArticleCategoryRelDO>lambdaQuery()
                .eq(ArticleCategoryRelDO::getArticleId, articleId));
    }

    /**
     * 根据文章id 查询分类id
     */
    default Long findCategoryByArticleId(Long articleId) {
        ArticleCategoryRelDO articleCategoryRelDO = selectOne(Wrappers.<ArticleCategoryRelDO>lambdaQuery()
                .eq(ArticleCategoryRelDO::getArticleId, articleId));
        return articleCategoryRelDO.getCategoryId();
    }

    /**
     * 根据分类 ID 查询
     */
    default ArticleCategoryRelDO selectOneByCategoryId(Long categoryId) {
        return selectOne(Wrappers.<ArticleCategoryRelDO>lambdaQuery()
                .eq(ArticleCategoryRelDO::getCategoryId, categoryId)
                .last("LIMIT 1"));
    }

    /**
     * 根据文章id 批量查询分类
     */
    default List<ArticleCategoryRelDO> selectByArticleIds(List<Long> articleIds) {
        return selectList(Wrappers.<ArticleCategoryRelDO>lambdaQuery()
                .in(ArticleCategoryRelDO::getArticleId, articleIds));
    }

    /**
     * 根据分类id 查询所有关联的文章
     */
    default List<ArticleCategoryRelDO> selectListByCategoryId(Long categoryId) {
        return selectList(Wrappers.<ArticleCategoryRelDO>lambdaQuery()
                .eq(ArticleCategoryRelDO::getCategoryId, categoryId));
    }
}