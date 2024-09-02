package com.smallfish.weblog.web.convert;

import com.smallfish.weblog.common.domain.dos.ArticleDO;
import com.smallfish.weblog.web.model.vo.archive.FindArchiveArticleRspVO;
import com.smallfish.weblog.web.model.vo.article.FindArticleDetailRspVO;
import com.smallfish.weblog.web.model.vo.article.FindIndexArticlePageListRspVO;
import com.smallfish.weblog.web.model.vo.category.FindCategoryArticlePageListRspVO;
import com.smallfish.weblog.web.model.vo.tag.FindTagArticlePageListRspVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Mapper
public interface ArticleConvert {
    ArticleConvert INSTANCE = Mappers.getMapper(ArticleConvert.class);

    // DO -》 VO
    @Mapping(target = "createDate", expression = "java(java.time.LocalDate.from(articleDO.getCreateTime()))")
    FindIndexArticlePageListRspVO doToVo(ArticleDO articleDO);

    @Mapping(target = "createDate", expression = "java(java.time.LocalDate.from(articleDO.getCreateTime()))")
    @Mapping(target = "createMonth", expression = "java(java.time.YearMonth.from(articleDO.getCreateTime()))")
    FindArchiveArticleRspVO articleToVo(ArticleDO articleDO);

    @Mapping(target = "createDate", expression = "java(java.time.LocalDate.from(articleDO.getCreateTime()))")
    FindCategoryArticlePageListRspVO categoryArticleToVo(ArticleDO articleDO);

    @Mapping(target = "createDate", expression = "java(java.time.LocalDate.from(articleDO.getCreateTime()))")
    FindTagArticlePageListRspVO tagArticleToVo(ArticleDO articleDO);

    FindArticleDetailRspVO articleToDetailVo(ArticleDO articleDO);

}
