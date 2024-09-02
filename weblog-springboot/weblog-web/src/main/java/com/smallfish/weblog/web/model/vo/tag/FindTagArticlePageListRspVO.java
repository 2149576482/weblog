package com.smallfish.weblog.web.model.vo.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindTagArticlePageListRspVO {

    /**
     * 分类id
     */
    private Long id;

    /**
     * 封面
     */
    private String cover;

    /**
     * 标题
     */
    private String title;

    /**
     * 发布时间
     */
    private LocalDate createDate;

}
