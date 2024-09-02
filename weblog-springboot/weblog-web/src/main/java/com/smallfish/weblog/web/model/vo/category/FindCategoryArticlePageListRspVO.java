package com.smallfish.weblog.web.model.vo.category;

import com.smallfish.weblog.common.model.BasePageQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindCategoryArticlePageListRspVO {

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
