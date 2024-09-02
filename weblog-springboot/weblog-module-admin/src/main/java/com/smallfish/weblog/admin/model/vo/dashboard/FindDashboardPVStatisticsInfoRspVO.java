package com.smallfish.weblog.admin.model.vo.dashboard;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindDashboardPVStatisticsInfoRspVO {

    private List<String> pvDates;
    private List<Long> pvCounts;
}
