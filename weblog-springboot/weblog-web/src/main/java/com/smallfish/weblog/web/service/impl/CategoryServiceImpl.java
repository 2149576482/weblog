package com.smallfish.weblog.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smallfish.weblog.common.domain.dos.ArticleCategoryRelDO;
import com.smallfish.weblog.common.domain.dos.ArticleDO;
import com.smallfish.weblog.common.domain.dos.CategoryDO;
import com.smallfish.weblog.common.domain.mapper.ArticleCategoryRelMapper;
import com.smallfish.weblog.common.domain.mapper.ArticleMapper;
import com.smallfish.weblog.common.domain.mapper.CategoryMapper;
import com.smallfish.weblog.common.enums.ResultCodeEnum;
import com.smallfish.weblog.common.exception.BusinessException;
import com.smallfish.weblog.common.utils.PageResponse;
import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.convert.ArticleConvert;
import com.smallfish.weblog.web.model.vo.category.FindCategoryArticlePageListReqVO;
import com.smallfish.weblog.web.model.vo.category.FindCategoryArticlePageListRspVO;
import com.smallfish.weblog.web.model.vo.category.FindCategoryListRspVO;
import com.smallfish.weblog.web.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleCategoryRelMapper articleCategoryRelMapper;

    /**
     * 获取全部分类
     */
    @Override
    public Result findCategoryList() {
        List<CategoryDO> categoryDOList = categoryMapper.selectList(null);
        List<FindCategoryListRspVO> findCategoryListRspVOS = new ArrayList<>();
        categoryDOList.forEach(categoryDO -> {
            FindCategoryListRspVO findCategoryListRspVO = FindCategoryListRspVO
                    .builder()
                    .id(categoryDO.getId())
                    .name(categoryDO.getName())
                    .build();
            findCategoryListRspVOS.add(findCategoryListRspVO);
        });
        return Result.ok(findCategoryListRspVOS);
    }

    /**
     * 获取分类对应文章
     */
    @Override
    public Result selectListByCategoryId(FindCategoryArticlePageListReqVO findCategoryArticlePageListReqVO) {

        Long current = findCategoryArticlePageListReqVO.getCurrent();
        Long size = findCategoryArticlePageListReqVO.getSize();
        Long categoryId = findCategoryArticlePageListReqVO.getId();

        CategoryDO categoryDO = categoryMapper.selectById(categoryId);

        // 判断该分类是否存在
        if (Objects.isNull(categoryDO)) {
            log.warn("==> 该分类不存在, categoryId: {}", categoryId);
            throw new BusinessException(ResultCodeEnum.CATEGORY_NOT_EXISTED);
        }

        // 根据分类id 查询到所有的分类文章对应关系
        List<ArticleCategoryRelDO> articleCategoryRelDOS = articleCategoryRelMapper.selectListByCategoryId(categoryId);
        List<Long> articleIds = articleCategoryRelDOS.stream().map(ArticleCategoryRelDO::getArticleId).collect(Collectors.toList());

        if (CollectionUtils.isEmpty(articleCategoryRelDOS)) {
            log.warn("==> 该分类下还未发布任何文章, categoryId: {}", categoryId);
            return PageResponse.success(null, null);
        }

        // 获取分页对象
        Page<ArticleDO> articleDOPage = articleMapper.selectPageListByArticleIds(current, size, articleIds);
        List<ArticleDO> articleDOS = articleDOPage.getRecords();

        // 返参列表
        List<FindCategoryArticlePageListRspVO> vos = null;

        // 遍历关系
        if (!CollectionUtils.isEmpty(articleDOS)) {

            vos = articleDOS.stream()
                    .map(ArticleConvert.INSTANCE::categoryArticleToVo)
                    .collect(Collectors.toList());

        }
        return PageResponse.success(articleDOPage, vos);
    }
}




















