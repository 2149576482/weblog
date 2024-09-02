package com.smallfish.weblog.search.runner;

import com.google.common.collect.Lists;
import com.smallfish.weblog.common.constant.Constants;
import com.smallfish.weblog.common.domain.dos.ArticleDO;
import com.smallfish.weblog.common.domain.mapper.ArticleContentMapper;
import com.smallfish.weblog.common.domain.mapper.ArticleMapper;
import com.smallfish.weblog.search.LuceneHelper;
import com.smallfish.weblog.search.config.LuceneProperties;
import com.smallfish.weblog.search.index.ArticleIndex;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 全文检索
 * CommandLineRunner 是 Spring Boot 提供的一个接口，
 * 用于在 Spring Boot 应用启动后执行一些初始化逻辑。它是一个功能接口，
 * 只包含一个 run 方法，该方法会在 Spring Boot 应用启动后被调用。
 **/
@Component
@Slf4j
public class InitLuceneIndexRunner implements CommandLineRunner {

    @Resource
    private LuceneProperties luceneProperties;

    @Resource
    private LuceneHelper luceneHelper;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleContentMapper articleContentMapper;
    @Override
    public void run(String... args) throws Exception {
        log.info("==> 开始初始化 Lucene 索引...");

        // 查询所有文章
        List<ArticleDO> articleDOList = articleMapper.selectList(null);
        if (CollectionUtils.isEmpty(articleDOList)) {
            log.warn("==> 未发布任何文章, 暂不创建 Lucene 索引...");
            return;
        }
        if (StringUtils.isBlank(luceneProperties.getIndexDir())) {
            log.warn("==> 未配置 Lucene 索引目录, 暂不创建 Lucene 索引... 需要先添加路径..");
            return;
        }

        List<Document> documents = Lists.newArrayList();

        articleDOList.forEach(articleDO -> {
            // 文章id
            Long articleId = articleDO.getId();
            // 正文
            String content = articleContentMapper.findContentByArticleId(articleId);

            if (StringUtils.isBlank(content)) {
                log.warn("==> 该文章没有内容, 无法创建");
            }

            // 构建文档
            Document document = new Document();
            document.add(new TextField(ArticleIndex.COLUMN_ID, String.valueOf(articleId), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_TITLE, articleDO.getTitle(), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_COVER, articleDO.getCover(), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_SUMMARY, articleDO.getSummary(), Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_CONTENT, content, Field.Store.YES));
            document.add(new TextField(ArticleIndex.COLUMN_CREATE_TIME, Constants.DATE_TIME_FORMATTER.format(articleDO.getCreateTime()), Field.Store.YES));;
            documents.add(document);
        });
        // 创建索引
        luceneHelper.createIndex(ArticleIndex.NAME, documents);

        log.info("==> 结束初始化索引... lucene");
    }
}
