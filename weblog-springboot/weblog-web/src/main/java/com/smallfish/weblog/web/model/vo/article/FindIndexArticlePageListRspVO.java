package com.smallfish.weblog.web.model.vo.article;

import com.smallfish.weblog.common.model.BasePageQuery;
import com.smallfish.weblog.web.model.vo.category.FindCategoryListRspVO;
import com.smallfish.weblog.web.model.vo.tag.FindTagListRspVO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindIndexArticlePageListRspVO {

    // 文章ID
    private Long id;

    // 文章封面
    private String cover;

    // 文章标题
    private String title;

    // 文章摘要
    private String summary;

    // 文章发布时间
    private LocalDate createDate;

    // 文章分类
    private FindCategoryListRspVO category;

    // 文章标签
    private List<FindTagListRspVO> tags;

}
