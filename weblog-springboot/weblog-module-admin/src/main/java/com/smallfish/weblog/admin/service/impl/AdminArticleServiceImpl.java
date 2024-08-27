package com.smallfish.weblog.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smallfish.weblog.admin.conver.ArticleConvert;
import com.smallfish.weblog.admin.model.vo.article.*;
import com.smallfish.weblog.admin.service.AdminArticleService;
import com.smallfish.weblog.common.domain.dos.*;
import com.smallfish.weblog.common.domain.mapper.*;
import com.smallfish.weblog.common.enums.ResultCodeEnum;
import com.smallfish.weblog.common.exception.BusinessException;
import com.smallfish.weblog.common.utils.PageResponse;
import com.smallfish.weblog.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Service
@Slf4j
public class AdminArticleServiceImpl implements AdminArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleContentMapper articleContentMapper;

    @Resource
    private ArticleCategoryRelMapper articleCategoryRelMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ArticleTagRelMapper articleTagRelMapper;

    @Resource
    private TagMapper tagMapper;

    /**
     * 发布文章
     *
     * @param publishArticleReqVO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result publishArticle(PublishArticleReqVO publishArticleReqVO) {
        // vo -> do
        ArticleDO articleDO = ArticleConvert.INSTANCE.articleVoToDo(publishArticleReqVO);
        articleDO.setCreateTime(LocalDateTime.now());
        articleDO.setUpdateTime(LocalDateTime.now());
        articleMapper.insert(articleDO);

        // 拿到插入记录的主键
        Long articleId = articleDO.getId();

        // vo 转ArticleContentDO
        ArticleContentDO articleContentDO = ArticleConvert.INSTANCE.articleVoToContentDo(publishArticleReqVO);
        articleContentDO.setArticleId(articleId);
        articleContentMapper.insert(articleContentDO);

        // 分类转换
        Long categoryId = publishArticleReqVO.getCategoryId();

        // 校验提交的分类是否存在
        CategoryDO categoryDO = categoryMapper.selectById(categoryId);
        if (Objects.isNull(categoryDO)) {
            log.warn("==> 分类不存在, categoryId: {}", categoryId);
            throw new BusinessException(ResultCodeEnum.CATEGORY_NOT_EXISTED);
        }
        ArticleCategoryRelDO articleCategoryRelDO = ArticleCategoryRelDO.builder()
                .categoryId(publishArticleReqVO.getCategoryId())
                .articleId(articleId)
                .build();
        articleCategoryRelMapper.insert(articleCategoryRelDO);

        // 保存文章关联的标签集合
        List<String> tags = publishArticleReqVO.getTags();
        insertTags(articleId, tags);
        return Result.ok();
    }

    /**
     * 删除文章
     */
    @Override
    public Result deleteArticle(DeleteArticleReqVO deleteArticleReqVO) {

        Long articleReqVOId = deleteArticleReqVO.getId();

        // 删除文章
        articleMapper.deleteById(articleReqVOId);

        // 删除对应文章内容
        articleContentMapper.deleteByArticleId(articleReqVOId);

        // 删除对应分类关系
        articleCategoryRelMapper.deleteByArticleId(articleReqVOId);

        // 删除对应标签关系
        articleTagRelMapper.deleteByArticleId(articleReqVOId);
        return Result.ok();

    }

    /**
     * 分页查询文章
     */
    @Override
    public Result findArticleList(FindArticlePageListReqVO findArticlePageListReqVO) {

        Long current = findArticlePageListReqVO.getCurrent();
        Long size = findArticlePageListReqVO.getSize();
        String searchTitle = findArticlePageListReqVO.getSearchTitle();
        LocalDate startDate = findArticlePageListReqVO.getStartDate();
        LocalDate endDate = findArticlePageListReqVO.getEndDate();


        Page<ArticleDO> articleDOPage = articleMapper.selectPageList(current, size, searchTitle, startDate, endDate);

        List<ArticleDO> articleDOList = articleDOPage.getRecords();

        List<FindArticlePageListRspVO> findArticlePageListRspVOList = null;
        // 如果不为空 进行转换
        if (!CollectionUtils.isEmpty(articleDOList)) {
            findArticlePageListRspVOList = ArticleConvert.INSTANCE.articleDoToRspVo(articleDOList);
        }
        return PageResponse.success(articleDOPage, findArticlePageListRspVOList);
    }

    /**
     * 查询文章详情
     */
    @Override
    public Result findCategorySelectList(FindArticleDetailReqVO findArticleDetailReqVO) {

        Long articleId = findArticleDetailReqVO.getId();

        // 查询文章
        ArticleDO articleDO = articleMapper.selectById(articleId);

        if (Objects.isNull(articleDO)) {
            log.warn("==> 查询的文章不存在, articleId: {}", articleId);
            throw new BusinessException(ResultCodeEnum.ARTICLE_NOT_FOUND);
        }

        // 查询文章内容
        String content = articleContentMapper.findContentByArticleId(articleId);

        // 查询文章分类
        Long categoryId = articleCategoryRelMapper.findCategoryByArticleId(articleId);

        // 查询标签
        List<Long> tagIds = articleTagRelMapper.findTagByArticleId(articleId);

        // DO -> VO
        FindArticleDetailRspVO findArticleDetailRspVO = ArticleConvert.INSTANCE.articleDoToDetailRspVo(articleDO);
        findArticleDetailRspVO.setContent(content);
        findArticleDetailRspVO.setCategoryId(categoryId);
        findArticleDetailRspVO.setTagIds(tagIds);

        return Result.ok(findArticleDetailRspVO);

    }

    /**
     * 更新文章
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result updateArticle(UpdateArticleReqVO updateArticleReqVO) {

        Long articleId = updateArticleReqVO.getId();
        String content = updateArticleReqVO.getContent();

        //  VO -> DO
        ArticleDO articleDO = ArticleConvert.INSTANCE.updateArticleReqVoToDo(updateArticleReqVO);
        int count = articleMapper.updateById(articleDO);

        // 根据更新是否成功 来判断该文章是否存在
        if (count == 0) {
            log.warn("==> 该文章不存在, articleId: {}", articleId);
            throw new BusinessException(ResultCodeEnum.ARTICLE_NOT_FOUND);
        }

        // 更新文章内容
        ArticleContentDO articleContentDO = ArticleContentDO.builder()
                .content(content)
                .articleId(articleId)
                .build();
        articleContentMapper.updateContentByArticleId(articleContentDO);

        // 更新分类
        Long categoryId = updateArticleReqVO.getCategoryId();

        // 校验提交的分类是否真实存在
        CategoryDO categoryDO = categoryMapper.selectById(categoryId);
        if (Objects.isNull(categoryDO)) {
            log.warn("==> 分类不存在, categoryId: {}", categoryId);
            throw new BusinessException(ResultCodeEnum.CATEGORY_NOT_EXISTED);
        }

        // 先删除该文章关联的分类记录 在插入新的关联关系
        articleCategoryRelMapper.deleteByArticleId(articleId);
        ArticleCategoryRelDO articleCategoryRelDO = ArticleCategoryRelDO.builder()
                .categoryId(categoryId)
                .articleId(articleId)
                .build();
        articleCategoryRelMapper.insert(articleCategoryRelDO);

        // 先删除对应的标签 在插入新的标签
        articleTagRelMapper.deleteByArticleId(articleId);
        List<String> tags = updateArticleReqVO.getTags();
        insertTags(articleId, tags);
        return Result.ok();


    }

    /**
     * 保存标签
     */
    private void insertTags(Long articleId, List<String> tags) {

        // 表中不存在的标签
        List<String> notExistTags = null;

        // 表中存在的标签
        List<String> existTags = null;

        // 获取数据库中的全部标签
        List<TagDO> tagDOS = tagMapper.selectList(null);

        // 如果表中没有标签
        if (CollectionUtils.isEmpty(tagDOS)) {
            notExistTags = tags;
        } else {
            List<String> tagIds = tagDOS.stream().map(tagDO -> String.valueOf(tagDO.getId())).collect(Collectors.toList());

            // 表中已添加相关标签，则需要筛选
            // 通过标签 ID 来筛选，包含对应 ID 则表示提交的标签是表中存在的
            existTags = tags.stream().filter(tagIds::contains).collect(Collectors.toList());
            // 否则是不存在的
            notExistTags = tags.stream().filter(publishTag -> !tagIds.contains(publishTag)).collect(Collectors.toList());

            // 还有一种情况
            // 字符串提交的标签 数据库可能也有 比方说 Java  提交的是java
            Map<String, Long> tagNameMap = tagDOS.stream()
                    .collect(Collectors.toMap(tagDO -> tagDO.getName().toLowerCase(), TagDO::getId));

            // 使用迭代器 进行安全的删除操作
            Iterator<String> iterator = notExistTags.iterator();
            while (iterator.hasNext()) {
                String notExistTag = iterator.next();
                // 转小写 判断 Map中是否有相同的标签
                if (tagNameMap.containsKey(notExistTag.toLowerCase())) {
                    // 从不存在的标签集合中清除
                    iterator.remove();
                    // 将对应的ID 添加到标签集合中
                    existTags.add(String.valueOf(tagNameMap.get(notExistTag.toLowerCase())));
                }
            }

            // 将提交上来的 已存在表中的标签 文章-标签关联关系入库
            if (!CollectionUtils.isEmpty(existTags)) {
                ArrayList<ArticleTagRelDO> arrayList = new ArrayList<>();
                existTags.forEach(tagId -> {
                    ArticleTagRelDO articleTagRelDO = ArticleTagRelDO.builder()
                            .articleId(articleId)
                            .tagId(Long.valueOf(tagId))
                            .build();
                    arrayList.add(articleTagRelDO);
                });
                articleTagRelMapper.insertBatchSomeColumn(arrayList);
            }

            // 将提交上来的 不存在表中的 入库保存
            if (!CollectionUtils.isEmpty(notExistTags)) {
                List<ArticleTagRelDO> tagDOList = new ArrayList<>();
                // 开始循环
                notExistTags.forEach(tagName -> {
                    TagDO tagDO = TagDO.builder()
                            .name(tagName)
                            .createTime(LocalDateTime.now())
                            .updateTime(LocalDateTime.now()).build();
                    tagMapper.insert(tagDO);

                    // 拿到保存的标签ID
                    Long tagId = tagDO.getId();

                    // 文章标签对应关系
                    ArticleTagRelDO articleTagRelDO = ArticleTagRelDO.builder()
                            .tagId(tagId).articleId(articleId).build();

                    tagDOList.add(articleTagRelDO);
                });
                // 循环结束 执行插入
                articleTagRelMapper.insertBatchSomeColumn(tagDOList);
            }
        }

    }
}
























