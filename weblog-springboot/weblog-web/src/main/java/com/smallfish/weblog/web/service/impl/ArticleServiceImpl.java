package com.smallfish.weblog.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smallfish.weblog.common.domain.dos.*;
import com.smallfish.weblog.common.domain.mapper.*;
import com.smallfish.weblog.common.utils.PageResponse;
import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.convert.ArticleConvert;
import com.smallfish.weblog.web.model.vo.article.FindIndexArticlePageListReqVO;
import com.smallfish.weblog.web.model.vo.article.FindIndexArticlePageListRspVO;
import com.smallfish.weblog.web.model.vo.category.FindCategoryListRspVO;
import com.smallfish.weblog.web.model.vo.tag.FindTagListRspVO;
import com.smallfish.weblog.web.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ArticleCategoryRelMapper articleCategoryRelMapper;

    @Resource
    private ArticleTagRelMapper articleTagRelMapper;

    @Resource
    private TagMapper tagMapper;

    /**
     * 获取文章首页分页数据
     */
    @Override
    public Result findArticlePageList(FindIndexArticlePageListReqVO findIndexArticlePageListReqVO) {

        Long size = findIndexArticlePageListReqVO.getSize();
        Long current = findIndexArticlePageListReqVO.getCurrent();

        // 获取page对象
        Page<ArticleDO> articleDOPage =
                articleMapper.selectPageList(current, size, null, null, null);

        // 分页数据
        List<ArticleDO> articleDOList = articleDOPage.getRecords();

        // 获取分页数据id
        List<Long> articleIds = articleDOList.stream().map(ArticleDO::getId).collect(Collectors.toList());

        List<FindIndexArticlePageListRspVO> collect = null;
        if (!CollectionUtils.isEmpty(articleDOList)) {

            // do -> vo
            collect = articleDOList.stream()
                    .map(ArticleConvert.INSTANCE::doToVo).collect(Collectors.toList());

            // 处理分类数据 把分类全部查出来 转换成map
            List<CategoryDO> categoryDOList = categoryMapper.selectList(null);
            Map<Long, String> categoryMap = categoryDOList
                    .stream()
                    .collect(Collectors.toMap(CategoryDO::getId, CategoryDO::getName));

            // 根据文章id 获取所有的分类记录
            List<ArticleCategoryRelDO> articleCategoryRelDOS =
                    articleCategoryRelMapper.selectByArticleIds(articleIds);

            // 遍历文章集合
            collect.forEach(vo -> {

                // 获取文章id
                Long articleDOId = vo.getId();

                // 过滤出当前文章关联的数据
                Optional<ArticleCategoryRelDO> optional = articleCategoryRelDOS.stream()
                        .filter(rel -> Objects.equals(rel.getArticleId(), articleDOId)).findAny();

                // 如果当前文章id 有分类数据
                if (optional.isPresent()) {

                    // 获取分类关系
                    ArticleCategoryRelDO articleCategoryRelDO = optional.get();
                    Long categoryId = articleCategoryRelDO.getCategoryId();

                    // 获取名字
                    String categoryName = categoryMap.get(categoryId);

                    // 出参 categoryVO
                    FindCategoryListRspVO findCategoryListRspVO = FindCategoryListRspVO.builder()
                            .id(categoryId)
                            .name(categoryName)
                            .build();
                    vo.setCategory(findCategoryListRspVO);
                }
            });

            // 3. 标签处理
            List<TagDO> tagDOS = tagMapper.selectList(null);
            // 根据全部的标签 转换成map
            Map<Long, String> tagMap = tagDOS.stream().collect(Collectors.toMap(TagDO::getId, TagDO::getName));

            // 获取到所有的文章标签对应关系
            List<ArticleTagRelDO> articleTagRelDOS = articleTagRelMapper.selectByArticleIds(articleIds);

            // 遍历文章集合
            collect.forEach(vo -> {
                Long articleId = vo.getId();

                // 获取文章标签对应关系
                List<ArticleTagRelDO> articleTagList = articleTagRelDOS
                        .stream()
                        .filter(rel -> Objects.equals(rel.getArticleId(), articleId))
                        .collect(Collectors.toList());

                List<FindTagListRspVO> findTagListRspVOList = new ArrayList<>();

                // 将关联记录转vo 并设置名字
                articleTagList.forEach(rel -> {
                    Long tagId = rel.getTagId();
                    String tagName = tagMap.get(tagId);
                    FindTagListRspVO findTagListRspVO = FindTagListRspVO.builder().id(tagId).name(tagName).build();
                    findTagListRspVOList.add(findTagListRspVO);
                });
                // 添加进集合
                vo.setTags(findTagListRspVOList);
            });
        }
        return PageResponse.success(articleDOPage, collect);
    }
}



























