package com.smallfish.weblog.web.service;

import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.model.vo.archive.FindIndexArchivePageListReqVO;
import com.smallfish.weblog.web.model.vo.article.FindArticleDetailReqVO;

public interface ArchiveService {

    /**
     * 归档数据分页查询
     */
    Result findArchiveList(FindIndexArchivePageListReqVO findIndexArchivePageListReqVO);
}
