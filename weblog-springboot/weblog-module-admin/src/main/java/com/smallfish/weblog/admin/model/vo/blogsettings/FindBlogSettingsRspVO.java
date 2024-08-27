package com.smallfish.weblog.admin.model.vo.blogsettings;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 博客设置入参VO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindBlogSettingsRspVO {

    /**
     * 博客Logo
     */
    private String logo;

    /**
     * 博客名称
     */
    private String name;

    /**
     * 作者名
     */
    private String author;

    /**
     * 介绍语
     */
    private String introduction;

    /**
     * 作者头像
     */
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
