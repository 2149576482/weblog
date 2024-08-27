package com.smallfish.weblog.admin.service;


import com.smallfish.weblog.admin.model.vo.tag.*;
import com.smallfish.weblog.common.utils.PageResponse;
import com.smallfish.weblog.common.utils.Result;

public interface AdminTagService {

    /**
     * 添加标签
     */
    Result addTag(AddTagReqVO addTagReqVO);

    /**
     * 标签分页查询
     */
    PageResponse findTagList(FindTagPageListReqVO findTagPageListReqVO);

    /**
     * 删除标签
     */
    Result deleteTag(DeleteTagReqVO deleteTagReqVO);

    /**
     * select 标签列表开发
     */
    Result findTagSelectList(SearchTagReqVO searchTagReqVO);

    /**
     * 获取所有下拉select
     * @return
     */
    Result findTagSearch();
}
