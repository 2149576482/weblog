package com.smallfish.weblog.admin.model.vo.dashboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindDashboardStatisticsInfoRspVO {

    private Long articleTotalCount;
    private Long categoryTotalCount;
    private Long tagTotalCount;

    // pv 总数
    private Long pvTotalCount;
}
