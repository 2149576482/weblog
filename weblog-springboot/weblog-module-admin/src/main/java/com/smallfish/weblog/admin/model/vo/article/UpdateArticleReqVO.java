package com.smallfish.weblog.admin.model.vo.article;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 *
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "更新文章 VO")
public class UpdateArticleReqVO {

    /**
     * 文章ID
     */
    @NotNull(message = "文章id不能为空")
    private Long id;

    /**
     * 文章标题
     */
    @NotBlank(message = "文章标题不能为空")
    @Length(min = 1, max = 40, message = "文章标题不能超过40个字符")
    private String title;

    /**
     * 文章内容
     */
    @NotBlank(message = "文章内容不能为空")
    private String content;

    /**
     * 文章封面
     */
    @NotBlank(message = "文章封面不能为空")
    private String cover;

    /**
     * 文章摘要
     */
    private String summary;

    /**
     * 文章分类
     */
    @NotNull(message = "文章分类不能为空")
    private Long categoryId;

    /**
     * 文章标签
     */
    @NotEmpty(message = "文章标签不能为空")
    private List<String> tags;

}
