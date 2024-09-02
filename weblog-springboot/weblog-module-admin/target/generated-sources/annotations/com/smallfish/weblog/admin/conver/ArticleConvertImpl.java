package com.smallfish.weblog.admin.conver;

import com.smallfish.weblog.admin.model.vo.article.FindArticleDetailRspVO;
import com.smallfish.weblog.admin.model.vo.article.FindArticlePageListRspVO;
import com.smallfish.weblog.admin.model.vo.article.PublishArticleReqVO;
import com.smallfish.weblog.admin.model.vo.article.UpdateArticleReqVO;
import com.smallfish.weblog.common.domain.dos.ArticleContentDO;
import com.smallfish.weblog.common.domain.dos.ArticleDO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-02T10:12:42+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
public class ArticleConvertImpl implements ArticleConvert {

    @Override
    public ArticleDO articleVoToDo(PublishArticleReqVO publishArticleReqVO) {
        if ( publishArticleReqVO == null ) {
            return null;
        }

        ArticleDO.ArticleDOBuilder articleDO = ArticleDO.builder();

        articleDO.title( publishArticleReqVO.getTitle() );
        articleDO.cover( publishArticleReqVO.getCover() );
        articleDO.summary( publishArticleReqVO.getSummary() );

        return articleDO.build();
    }

    @Override
    public ArticleContentDO articleVoToContentDo(PublishArticleReqVO publishArticleReqVO) {
        if ( publishArticleReqVO == null ) {
            return null;
        }

        ArticleContentDO.ArticleContentDOBuilder articleContentDO = ArticleContentDO.builder();

        articleContentDO.content( publishArticleReqVO.getContent() );

        return articleContentDO.build();
    }

    @Override
    public List<FindArticlePageListRspVO> articleDoToRspVo(List<ArticleDO> articleDOList) {
        if ( articleDOList == null ) {
            return null;
        }

        List<FindArticlePageListRspVO> list = new ArrayList<FindArticlePageListRspVO>( articleDOList.size() );
        for ( ArticleDO articleDO : articleDOList ) {
            list.add( articleDOToFindArticlePageListRspVO( articleDO ) );
        }

        return list;
    }

    @Override
    public FindArticleDetailRspVO articleDoToDetailRspVo(ArticleDO articleDO) {
        if ( articleDO == null ) {
            return null;
        }

        FindArticleDetailRspVO.FindArticleDetailRspVOBuilder findArticleDetailRspVO = FindArticleDetailRspVO.builder();

        findArticleDetailRspVO.id( articleDO.getId() );
        findArticleDetailRspVO.title( articleDO.getTitle() );
        findArticleDetailRspVO.cover( articleDO.getCover() );
        findArticleDetailRspVO.summary( articleDO.getSummary() );

        return findArticleDetailRspVO.build();
    }

    @Override
    public ArticleDO updateArticleReqVoToDo(UpdateArticleReqVO updateArticleReqVO) {
        if ( updateArticleReqVO == null ) {
            return null;
        }

        ArticleDO.ArticleDOBuilder articleDO = ArticleDO.builder();

        articleDO.id( updateArticleReqVO.getId() );
        articleDO.title( updateArticleReqVO.getTitle() );
        articleDO.cover( updateArticleReqVO.getCover() );
        articleDO.summary( updateArticleReqVO.getSummary() );

        return articleDO.build();
    }

    protected FindArticlePageListRspVO articleDOToFindArticlePageListRspVO(ArticleDO articleDO) {
        if ( articleDO == null ) {
            return null;
        }

        FindArticlePageListRspVO.FindArticlePageListRspVOBuilder findArticlePageListRspVO = FindArticlePageListRspVO.builder();

        findArticlePageListRspVO.id( articleDO.getId() );
        findArticlePageListRspVO.title( articleDO.getTitle() );
        findArticlePageListRspVO.cover( articleDO.getCover() );
        findArticlePageListRspVO.createTime( articleDO.getCreateTime() );

        return findArticlePageListRspVO.build();
    }
}
