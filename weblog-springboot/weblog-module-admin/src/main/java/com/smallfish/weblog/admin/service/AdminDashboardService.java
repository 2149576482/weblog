package com.smallfish.weblog.admin.service;

import com.smallfish.weblog.common.utils.Result;

public interface AdminDashboardService {

    /**
     * 获取后台仪表盘基础统计信息
     */
    Result findDashboardStatisticsInfo();

    /**
     * 获取文章发布热点统计信息
     */
    Result findDashboardPublishArticleStatistics();

    /**
     * 获取最近一周pv统计信息
     */
    Result findDashboardPvStatistics();
}
