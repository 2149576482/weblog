package com.smallfish.weblog.admin.model.vo.article;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * 文章表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "发布文章 VO")
public class PublishArticleReqVO {

    /**
     * 文章标题
     */
    @NotBlank(message = "文章标题不能为空")
    @Length(min = 1, max = 40, message = "文章标题长度需大于1 小于 40")
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
    @NotBlank(message = "文章分类不能为空")
    private Long categoryId;

    /**
     * 文章标签
     */
    @NotBlank(message = "文章标签不能为空")
    private List<String> tags;


}