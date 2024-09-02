package com.smallfish.weblog.web.service;

import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.model.vo.search.SearchArticlePageListReqVO;

public interface SearchService {

    /**
     * lucene 进行搜索
     */
    Result searchArticlePageList(SearchArticlePageListReqVO searchArticlePageListReqVO);
}
