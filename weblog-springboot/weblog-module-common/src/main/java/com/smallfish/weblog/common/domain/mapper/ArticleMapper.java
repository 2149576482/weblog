package com.smallfish.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smallfish.weblog.common.domain.dos.ArticleDO;
import com.smallfish.weblog.common.domain.dos.ArticlePublishCountDO;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
public interface ArticleMapper extends BaseMapper<ArticleDO> {

    default Page<ArticleDO> selectPageList(Long current, Long size, String title, LocalDate startDate, LocalDate endDate) {

        // 分页对象
        Page<ArticleDO> page = new Page<>(current, size);

        LambdaQueryWrapper<ArticleDO> wrapper = new LambdaQueryWrapper<>();

        wrapper.like(StringUtils.isNotBlank(title), ArticleDO::getTitle, title)
                .ge(Objects.nonNull(startDate), ArticleDO::getCreateTime, startDate)
                .le(Objects.nonNull(endDate), ArticleDO::getCreateTime, endDate)
                .orderByDesc(ArticleDO::getCreateTime);

        return selectPage(page, wrapper);
    }

    /**
     * 查询上一篇文章
     */
    default ArticleDO selectPreArticle(Long articleId) {
        return selectOne(Wrappers.<ArticleDO>lambdaQuery()
                .orderByAsc(ArticleDO::getId)
                .gt(ArticleDO::getId, articleId)
                .last("limit 1"));
    }

    /**
     * 查询下一篇文章
     */
    default ArticleDO selectNextArticle(Long articleId) {
        return selectOne(Wrappers.<ArticleDO>lambdaQuery()
                .orderByDesc(ArticleDO::getId)
                .lt(ArticleDO::getId, articleId)
                .last("limit 1"));
    }

    /**
     * 阅读+1
     */
    default int updateReadCount(Long articleId) {
        return update(null, Wrappers.<ArticleDO>lambdaUpdate()
                .setSql("read_num = read_num + 1")
                .eq(ArticleDO::getId, articleId));
    }

    /**
     * 根据浏览量进行查询
     */
    default List<ArticleDO> selectAllReadNum() {
        return selectList(Wrappers.<ArticleDO>lambdaQuery()
                .select(ArticleDO::getReadNum));
    }

    /**
     * 按日分组 查询当日发布的文章
     */
    @Select("select Date(create_time) date, COUNT(*) count " +
            "   from t_article " +
            "where create_time >= #{startDate} and create_time < #{endDate} " +
            "group by Date(create_time)")
    List<ArticlePublishCountDO> selectDateArticlePublishCount(LocalDate startDate, LocalDate endDate);
}
