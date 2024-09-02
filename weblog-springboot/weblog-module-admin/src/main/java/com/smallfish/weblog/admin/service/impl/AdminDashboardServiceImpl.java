package com.smallfish.weblog.admin.service.impl;

import com.smallfish.weblog.admin.model.vo.dashboard.FindDashboardPVStatisticsInfoRspVO;
import com.smallfish.weblog.admin.model.vo.dashboard.FindDashboardStatisticsInfoRspVO;
import com.smallfish.weblog.admin.service.AdminDashboardService;
import com.smallfish.weblog.common.constant.Constants;
import com.smallfish.weblog.common.domain.dos.ArticleDO;
import com.smallfish.weblog.common.domain.dos.ArticlePublishCountDO;
import com.smallfish.weblog.common.domain.dos.StatisticsArticlePvDO;
import com.smallfish.weblog.common.domain.mapper.ArticleMapper;
import com.smallfish.weblog.common.domain.mapper.CategoryMapper;
import com.smallfish.weblog.common.domain.mapper.StatisticsArticlePvMapper;
import com.smallfish.weblog.common.domain.mapper.TagMapper;
import com.smallfish.weblog.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sun.util.resources.cldr.mg.LocaleNames_mg;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Service
@Slf4j
public class AdminDashboardServiceImpl implements AdminDashboardService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private TagMapper tagMapper;

    @Resource
    private StatisticsArticlePvMapper statisticsArticlePvMapper;

    /**
     * 获取后台仪表盘基础统计信息
     */
    @Override
    public Result findDashboardStatisticsInfo() {

        // 获取文章统计信息
        Long articleCount = articleMapper.selectCount(null);

        // 获取分类统计信息
        Long categoryCount = categoryMapper.selectCount(null);

        // 获取标签统计信息
        Long tagCount = tagMapper.selectCount(null);

        // 获取浏览量
        List<ArticleDO> articleDOList = articleMapper.selectAllReadNum();
        Long pvTotalCount = 0L;
        if (!CollectionUtils.isEmpty(articleDOList)) {
            pvTotalCount = articleDOList.stream().mapToLong(ArticleDO::getReadNum).sum();
        }

        // 构建vo
        FindDashboardStatisticsInfoRspVO findDashboardStatisticsInfoRspVO = FindDashboardStatisticsInfoRspVO.builder()
                .articleTotalCount(articleCount)
                .categoryTotalCount(categoryCount)
                .tagTotalCount(tagCount)
                .pvTotalCount(pvTotalCount)
                .build();
        return Result.ok(findDashboardStatisticsInfoRspVO);
    }

    /**
     * 获取后台仪表盘文章发布热点统计信息
     */
    @Override
    public Result findDashboardPublishArticleStatistics() {
        // 当前日期
        LocalDate now = LocalDate.now();
        // 倒退一年的日期
        LocalDate startDate = now.minusYears(1);
        // 查询过去一年 今天也算
        List<ArticlePublishCountDO> articlePublishCountDOS =
                articleMapper.selectDateArticlePublishCount(startDate, now.plusDays(1));

        Map<LocalDate, Long> dateMap = null;

        if (!CollectionUtils.isEmpty(articlePublishCountDOS)) {
            Map<LocalDate, Long> collect = articlePublishCountDOS.stream()
                    .collect(Collectors.toMap(ArticlePublishCountDO::getDate, ArticlePublishCountDO::getCount));
            dateMap = new LinkedHashMap<>();
            // 从上一年的今天循环到今天
            /*
             * 1. startDate.isBefore(now) 如果开始日期在当前日期之前，则跳出循环
             * 2. startDate.isEqual(now) 如果开始日期等于当前日期，则跳出循环
             * 3. startDate = startDate.plusDays(1) 按照天数递增
             */
            for (; startDate.isBefore(now) || startDate.isEqual(now); startDate = startDate.plusDays(1)) {
                Long count = collect.get(startDate);
                dateMap.put(startDate, count == null ? 0L : count);
            }
        }
        return Result.ok(dateMap);
    }

    /**
     * 获取后台最近一周 pv 统计信息
     */
    @Override
    public Result findDashboardPvStatistics() {

        // 最近一周的访问记录
        List<StatisticsArticlePvDO> pvCountByDate = statisticsArticlePvMapper.findPvCountByDate();

        Map<LocalDate, Long> dateMap = new HashMap<>();
        // 如果不为空
        if (!CollectionUtils.isEmpty(pvCountByDate)) {
            dateMap = pvCountByDate.stream().collect(Collectors.toMap(StatisticsArticlePvDO::getPvDate, StatisticsArticlePvDO::getPvCount));
        }

        // 日期集合
        List<String> pvDates = new ArrayList<>();

        // pv 集合
        List<Long> pvCounts = new ArrayList<>();

        // 当前日期
        LocalDate now = LocalDate.now();

        // 一周前日期
        LocalDate localDate = now.plusDays(1).minusWeeks(1);

        // 出参对象
        FindDashboardPVStatisticsInfoRspVO findDashboardPVStatisticsInfoRspVO = null;

        // 从一周前开始遍历
        for( ; localDate.isBefore(now) || localDate.isEqual(now); localDate = localDate.plusDays(1)) {

            // 设置对应日期
            pvDates.add(localDate.format(Constants.dateTimeFormatter));

            // 设置日期对应pv
            Long pvCount = dateMap.get(localDate);
            pvCounts.add(pvCount == null ? 0 : pvCount);
        }

        findDashboardPVStatisticsInfoRspVO = FindDashboardPVStatisticsInfoRspVO.builder()
                .pvDates(pvDates)
                .pvCounts(pvCounts)
                .build();
        return Result.ok(findDashboardPVStatisticsInfoRspVO);
    }
}
