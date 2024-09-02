package com.smallfish.weblog.web.model.vo.search;

import com.smallfish.weblog.common.model.BasePageQuery;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "首页查询文章分页 VO")
public class SearchArticlePageListReqVO extends BasePageQuery {

    // 查询关键词
    private String word;

}