package com.smallfish.weblog.web.model.vo.article;

import com.smallfish.weblog.web.model.vo.tag.FindTagListRspVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindArticleDetailRspVO {

    // 标题
    private String title;

    // 正文
    private String content;

    // 创建时间
    private LocalDateTime createTime;

    // 分类ID
    private Long categoryId;

    // 分类名称
    private String categoryName;

    // 阅读数量
    private String readNum;

    // 标签
    private List<FindTagListRspVO> tags;

    // 上一篇文章
    private PreNextArticle preArticle;

    // 下一篇文章
    private PreNextArticle nextArticle;

}
