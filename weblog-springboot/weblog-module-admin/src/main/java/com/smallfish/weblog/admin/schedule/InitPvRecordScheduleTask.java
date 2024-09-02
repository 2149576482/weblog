package com.smallfish.weblog.admin.schedule;

import com.smallfish.weblog.common.domain.dos.StatisticsArticlePvDO;
import com.smallfish.weblog.common.domain.mapper.StatisticsArticlePvMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Component
@Slf4j
public class InitPvRecordScheduleTask {

    @Resource
    private StatisticsArticlePvMapper statisticsArticlePvMapper;

    // 每天晚间23点执行
    @Scheduled(cron = "0 0 23 * * ?")
    public void execute(){

        // 统计文章pv
        log.info("==> 开始执行初始化明日 pv 访问量定时任务 ");

        LocalDate now = LocalDate.now();
        LocalDate tomorrowDate = now.plusDays(1);

        // 组装插入的记录
         StatisticsArticlePvDO statisticsArticlePvDO = StatisticsArticlePvDO.builder()
                 .pvDate(tomorrowDate)
                 .pvCount(0L)
                 .createTime(LocalDateTime.now())
                 .updateTime(LocalDateTime.now())
                 .build();

         statisticsArticlePvMapper.insert(statisticsArticlePvDO);
         log.info("==> 初始化明日 pv 访问量定时任务 执行完成 ");
    }
}
