package com.smallfish.weblog.admin.event.subscriber;

import com.smallfish.weblog.admin.event.UpdateArticleEvent;
import com.smallfish.weblog.common.constant.Constants;
import com.smallfish.weblog.common.domain.dos.ArticleContentDO;
import com.smallfish.weblog.common.domain.dos.ArticleDO;
import com.smallfish.weblog.common.domain.mapper.ArticleContentMapper;
import com.smallfish.weblog.common.domain.mapper.ArticleMapper;
import com.smallfish.weblog.search.LuceneHelper;
import com.smallfish.weblog.search.index.ArticleIndex;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.Term;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Slf4j
@Component
public class UpdateArticleSubscriber implements ApplicationListener<UpdateArticleEvent> {

    @Resource
    private LuceneHelper luceneHelper;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleContentMapper articleContentMapper;

    @Override
    public void onApplicationEvent(UpdateArticleEvent event) {

        // 在这里处理收到的事件，可以是任何逻辑操作
        Long articleId = event.getArticleId();

        // 获取当前线程名称
        String threadName = Thread.currentThread().getName();

        log.info("==> threadName: {}", threadName);
        log.info("==> 文章更新事件消费成功，articleId: {}", articleId);

        // 查询文章数据
        ArticleDO articleDO = articleMapper.selectById(articleId);
        String content = articleContentMapper.findContentByArticleId(articleId);

        // 构建文档
        Document document = new Document();
        document.add(new TextField(ArticleIndex.COLUMN_ID, String.valueOf(articleId), Field.Store.YES));
        document.add(new TextField(ArticleIndex.COLUMN_TITLE, articleDO.getTitle(), Field.Store.YES));
        document.add(new TextField(ArticleIndex.COLUMN_COVER, articleDO.getCover(), Field.Store.YES));
        document.add(new TextField(ArticleIndex.COLUMN_SUMMARY, articleDO.getSummary(), Field.Store.YES));
        document.add(new TextField(ArticleIndex.COLUMN_CONTENT, content, Field.Store.YES));
        document.add(new TextField(ArticleIndex.COLUMN_CREATE_TIME, Constants.DATE_TIME_FORMATTER.format(articleDO.getCreateTime()), Field.Store.YES));

        // 更新条件（通过文章 ID 来更新）
        Term condition = new Term(ArticleIndex.COLUMN_ID, String.valueOf(articleId));

        long count = luceneHelper.updateDocument(ArticleIndex.NAME, document, condition);

        log.info("==> 更新文章对应 Lucene 文档结束，articleId: {}，受影响行数: {}", articleId, count);

    }
}
