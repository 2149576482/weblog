package com.smallfish.weblog.admin.event.subscriber;

import com.smallfish.weblog.admin.event.DeleteArticleEvent;
import com.smallfish.weblog.search.LuceneHelper;
import com.smallfish.weblog.search.index.ArticleIndex;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.index.Term;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 文章删除事件订阅者
 **/
@Component
@Slf4j
public class DeleteArticleSubscriber implements ApplicationListener<DeleteArticleEvent> {

    @Resource
    private LuceneHelper luceneHelper;
    /**
     * 删除文章
     * 使用@Async("threadPoolTaskExecutor")注解标记的方法会在调用时由threadPoolTaskExecutor线程池中的线程异步执行
     * 调用该方法 不会等待其完成 立即返回 提高响应速度
     * 需要配合@EnableAsync 在配置类中启用异步支持
     *
     */
    @Override
    @Async("threadPoolTaskExecutor")
    public void onApplicationEvent(DeleteArticleEvent event) {

        // 在这里处理收到的事件，可以是任何逻辑操作
        Long articleId = event.getArticleId();

        // 获取当前线程名称
        String threadName = Thread.currentThread().getName();

        log.info("==> threadName: {}", threadName);
        log.info("==> 文章删除事件消费成功，articleId: {}", articleId);

        // 删除条件，根据文章 id 来删除
        Term condition = new Term(ArticleIndex.COLUMN_ID, String.valueOf(articleId));

        long count = luceneHelper.deleteDocument(ArticleIndex.NAME, condition);

        log.info("==> 删除文章对应 Lucene 文档结束，articleId: {}，受影响行数: {}", articleId, count);
    }
}
