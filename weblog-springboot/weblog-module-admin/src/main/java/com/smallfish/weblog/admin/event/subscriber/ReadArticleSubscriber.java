package com.smallfish.weblog.admin.event.subscriber;

import com.smallfish.weblog.admin.event.ReadArticleEvent;
import com.smallfish.weblog.common.constant.Constants;
import com.smallfish.weblog.common.domain.dos.ArticleContentDO;
import com.smallfish.weblog.common.domain.dos.ArticleDO;
import com.smallfish.weblog.common.domain.mapper.ArticleContentMapper;
import com.smallfish.weblog.common.domain.mapper.ArticleMapper;
import com.smallfish.weblog.common.domain.mapper.StatisticsArticlePvMapper;
import com.smallfish.weblog.search.LuceneHelper;
import com.smallfish.weblog.search.index.ArticleIndex;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 监听文章阅读事件
 **/
@Component
@Slf4j
public class ReadArticleSubscriber implements ApplicationListener<ReadArticleEvent> {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private StatisticsArticlePvMapper statisticsArticlePvMapper;

    @Override
    @Async("threadPoolTaskExecutor")
    public void onApplicationEvent(ReadArticleEvent event) {

        // 这里处理收到的事件 可以是任何逻辑操作
        Long articleId = event.getArticleId();

        // 获取当前线程名字
        String name = Thread.currentThread().getName();

        log.info("==> threadName: {}", name);
        log.info("==> 文章阅读事件消费成功, articleId: {}", articleId);

        articleMapper.updateReadCount(articleId);
        log.info("==> 更新文章阅读量 +1 成功, articleId: {}", articleId);

        LocalDate currDate = LocalDate.now();
        statisticsArticlePvMapper.updatePvCount(currDate);
        log.info("==> 更新今日 pv 访问量 +1 成功, currDate: {}", currDate);
    }
}
