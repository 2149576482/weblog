package com.smallfish.weblog.admin.conver;

import com.smallfish.weblog.admin.model.vo.article.FindArticleDetailRspVO;
import com.smallfish.weblog.admin.model.vo.article.FindArticlePageListRspVO;
import com.smallfish.weblog.admin.model.vo.article.PublishArticleReqVO;
import com.smallfish.weblog.admin.model.vo.article.UpdateArticleReqVO;
import com.smallfish.weblog.common.domain.dos.ArticleContentDO;
import com.smallfish.weblog.common.domain.dos.ArticleDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Mapper
public interface ArticleConvert {

    ArticleConvert INSTANCE = Mappers.getMapper(ArticleConvert.class);

    // VO -> DO
    ArticleDO articleVoToDo(PublishArticleReqVO publishArticleReqVO);

    // VO -> DO
    ArticleContentDO articleVoToContentDo(PublishArticleReqVO publishArticleReqVO);

    // List<ArticleDO> -> List<FindArticleRspVO>
    List<FindArticlePageListRspVO> articleDoToRspVo(List<ArticleDO> articleDOList);

    // ArticleDO -> FindArticleDetailVO
    FindArticleDetailRspVO articleDoToDetailRspVo(ArticleDO articleDO);

    // UpdateArticleReqVO -> ArticleDO
    ArticleDO updateArticleReqVoToDo(UpdateArticleReqVO updateArticleReqVO);

}
