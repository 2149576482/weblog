package com.smallfish.weblog.web.model.vo.search;

import com.smallfish.weblog.web.model.vo.category.FindCategoryListRspVO;
import com.smallfish.weblog.web.model.vo.tag.FindTagListRspVO;
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
public class SearchArticlePageListRspVO {

    // 文章ID
    private Long id;

    // 文章封面
    private String cover;

    // 文章标题
    private String title;

    // 文章摘要
    private String summary;

    // 文章发布时间
    private String createDate;

}
