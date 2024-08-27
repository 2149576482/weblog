package com.smallfish.weblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smallfish.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.smallfish.weblog.admin.model.vo.category.FindCategoryPageListRspVO;
import com.smallfish.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.smallfish.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.smallfish.weblog.admin.service.AdminCategoryService;
import com.smallfish.weblog.common.domain.dos.ArticleCategoryRelDO;
import com.smallfish.weblog.common.domain.dos.CategoryDO;
import com.smallfish.weblog.common.domain.mapper.ArticleCategoryRelMapper;
import com.smallfish.weblog.common.domain.mapper.CategoryMapper;
import com.smallfish.weblog.common.enums.ResultCodeEnum;
import com.smallfish.weblog.common.exception.BusinessException;
import com.smallfish.weblog.common.model.vo.SelectRspVO;
import com.smallfish.weblog.common.utils.PageResponse;
import com.smallfish.weblog.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 分类相关接口
 **/
@Service
@Slf4j
public class AdminCategoryServiceImpl implements AdminCategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private ArticleCategoryRelMapper articleCategoryRelMapper;

    /**
     * 添加分类
     */
    @Override
    public Result addCategory(AddCategoryReqVO addCategoryReqVO) {

        String categoryName = addCategoryReqVO.getName();
        // 判断该分类是否存在
        CategoryDO categoryDO = categoryMapper.selectByName(categoryName);

        if (Objects.nonNull(categoryDO)) {
            log.warn("分类名称: {}, 已存在", categoryName);
            throw new BusinessException(ResultCodeEnum.CATEGORY_NAME_IS_EXISTED);
        }

        // 不存在就插入 构建DO
        CategoryDO insertCategoryDO = CategoryDO.builder()
                .name(categoryName.trim())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();

        // 返回结果
        categoryMapper.insert(insertCategoryDO);

        return Result.ok();

    }

    /**
     * 分页查询
     */
    @Override
    public PageResponse findCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO) {

        // 获取当前页 当前页所需数据
        Long current = findCategoryPageListReqVO.getCurrent();
        Long size = findCategoryPageListReqVO.getSize();

        // 分页对象 查询第几页 每页多少数量
        Page<CategoryDO> page = new Page<>(current, size);

        // 构建查询条件
        LambdaQueryWrapper<CategoryDO> wrapper = new LambdaQueryWrapper<>();

        String name = findCategoryPageListReqVO.getName();
        LocalDate startDate = findCategoryPageListReqVO.getStartDate();
        LocalDate endDate = findCategoryPageListReqVO.getEndDate();

        // 如果name不为空 则判断 传入name 和 数据库name模糊匹配
        wrapper.like(StringUtils.isNotBlank(name), CategoryDO::getName, name)
                .ge(Objects.nonNull(startDate), CategoryDO::getCreateTime, startDate)
                .le(Objects.nonNull(endDate), CategoryDO::getCreateTime, endDate)
                .orderByDesc(CategoryDO::getCreateTime);

        // 执行分页查询
        Page<CategoryDO> categoryDOPage = categoryMapper.selectPage(page, wrapper);

        List<CategoryDO> categoryDOList = categoryDOPage.getRecords();

        // DO -> VO
        List<FindCategoryPageListRspVO> vos = null;

        if (!CollectionUtils.isEmpty(categoryDOList)) {
            vos = categoryDOList.stream()
                    .map(categoryDO -> FindCategoryPageListRspVO.builder()
                            .id(categoryDO.getId())
                            .name(categoryDO.getName())
                            .startDate(categoryDO.getCreateTime())
                            .build()).collect(Collectors.toList());
        }

        // 封装
        return PageResponse.success(categoryDOPage, vos);

    }

    /**
     * 删除分类
     */
    @Override
    public Result deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO) {

        // 分类id
        Long categoryId = deleteCategoryReqVO.getId();

        // 校验该分类是否有文章 如果有 则提示先删除文章 在删除分类
        ArticleCategoryRelDO articleCategoryRelDO = articleCategoryRelMapper.selectOneByCategoryId(categoryId);
        if (Objects.nonNull(articleCategoryRelDO)) {
            log.warn("==> 此分类下有文章，无法删除 categoryId: {}", categoryId);
            throw new BusinessException(ResultCodeEnum.CATEGORY_CAN_NOT_DELETE);
        }

        categoryMapper.deleteById(categoryId);

        return Result.ok();
    }

    /**
     * 查询分类列表
     */
    @Override
    public Result findCategorySelectList() {

        List<CategoryDO> categoryDOList = categoryMapper.selectList(null);

        List<SelectRspVO> selectRspVOS = null;

        selectRspVOS = categoryDOList.stream()
                .map(categoryDO -> SelectRspVO.builder()
                        .label(categoryDO.getName()).value(categoryDO.getId())
                        .build()).collect(Collectors.toList());

        return Result.ok(selectRspVOS);

    }
}
