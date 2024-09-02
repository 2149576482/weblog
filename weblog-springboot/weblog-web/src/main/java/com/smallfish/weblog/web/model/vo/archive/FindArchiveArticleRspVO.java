package com.smallfish.weblog.web.model.vo.archive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindArchiveArticleRspVO {

    // 文章ID
    private Long id;

    // 文章封面
    private String cover;

    // 文章标题
    private String title;

    // 文章发布时间
    private LocalDate createDate;

    private YearMonth createMonth;

}
