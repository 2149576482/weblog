package com.smallfish.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.smallfish.weblog.common.domain.dos.StatisticsArticlePvDO;

import java.sql.Wrapper;
import java.time.LocalDate;
import java.util.List;

public interface StatisticsArticlePvMapper extends BaseMapper<StatisticsArticlePvDO> {

    /**
     * 对指定日期的文章 pv 访问量 +1
     */
    default int updatePvCount(LocalDate date) {
        return update(null, Wrappers.<StatisticsArticlePvDO>lambdaUpdate()
                .setSql("pv_count = pv_count+1")
                .eq(StatisticsArticlePvDO::getPvDate, date));
    }

    /**
     * 获取最近一周 pv 访问量
     */
    default List<StatisticsArticlePvDO> findPvCountByDate() {
        return selectList(Wrappers.<StatisticsArticlePvDO>lambdaQuery()
                .le(StatisticsArticlePvDO::getPvDate, LocalDate.now().plusDays(1))
                .orderByDesc(StatisticsArticlePvDO::getPvDate)
                .last("limit 7"));
    }

}
