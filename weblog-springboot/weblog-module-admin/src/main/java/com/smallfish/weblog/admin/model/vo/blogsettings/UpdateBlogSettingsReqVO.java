package com.smallfish.weblog.admin.model.vo.blogsettings;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 博客设置入参VO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "博客基础信息修改 入参 VO")
public class UpdateBlogSettingsReqVO {

    /**
     * 博客Logo
     */
    @NotBlank(message = "博客Logo不能为空")
    private String logo;

    /**
     * 博客名称
     */
    @NotBlank(message = "博客名称不能为空")
    private String name;

    /**
     * 作者名
     */
    @NotBlank(message = "博客作者不能为空")
    private String author;

    /**
     * 介绍语
     */
    @NotBlank(message = "博客介绍语不能为空")
    private String introduction;

    /**
     * 作者头像
     */
    @NotBlank(message = "博客头像不能为空")
    private String avatar;

    /**
     * GitHub 主页访问地址
     */
    private String githubHomepage;

    /**
     * CSDN 主页访问地址
     */
    private String csdnHomepage;

    /**
     * Gitee 主页访问地址
     */
    private String giteeHomepage;

    /**
     * 知乎主页访问地址
     */
    private String zhihuHomepage;

}
