package com.smallfish.weblog.web.convert;

import com.smallfish.weblog.common.domain.dos.ArticleDO;
import com.smallfish.weblog.web.model.vo.article.FindIndexArticlePageListRspVO;
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
}
