package com.smallfish.weblog.web.model.vo.archive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindIndexArchivePageListRspVO {

    /**
     * 归档月份
     */
    private YearMonth month;

    /**
     * 文章列表
     */
    private List<FindArchiveArticleRspVO> articles;

}
