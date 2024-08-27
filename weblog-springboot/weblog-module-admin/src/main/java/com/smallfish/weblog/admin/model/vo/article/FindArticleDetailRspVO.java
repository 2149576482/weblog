package com.smallfish.weblog.admin.model.vo.article;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@ApiModel(value = "查询文章详情 出参 VO")
public class FindArticleDetailRspVO {

    // 文章ID
    private Long id;

    // 文章标题
    private String title;

    // 封面
    private String cover;

    // 内容
    private String content;

    // 分类
    private Long categoryId;

    // 标签
    private List<Long> tagIds;

    // 摘要
    private String summary;


}
