package com.smallfish.weblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smallfish.weblog.admin.model.vo.tag.*;
import com.smallfish.weblog.admin.service.AdminTagService;
import com.smallfish.weblog.common.domain.dos.ArticleTagRelDO;
import com.smallfish.weblog.common.domain.dos.TagDO;
import com.smallfish.weblog.common.domain.mapper.ArticleTagRelMapper;
import com.smallfish.weblog.common.domain.mapper.TagMapper;
import com.smallfish.weblog.common.enums.ResultCodeEnum;
import com.smallfish.weblog.common.exception.BusinessException;
import com.smallfish.weblog.common.model.vo.SelectRspVO;
import com.smallfish.weblog.common.utils.PageResponse;
import com.smallfish.weblog.common.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 标签service实现类
 **/
@Service
@Slf4j
public class AdminTagServiceImpl extends ServiceImpl<TagMapper, TagDO> implements AdminTagService {

    @Resource
    private TagMapper tagMapper;

    @Resource
    private ArticleTagRelMapper articleTagRelMapper;

    /**
     * 添加标签集合
     */
    @Override
    public Result addTag(AddTagReqVO addTagReqVO) {

        List<TagDO> tags = addTagReqVO.getTags()
                .stream()
                .map(tagName -> TagDO.builder()
                        .name(tagName)
                        .createTime(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());

        // 继承mybatis-plus的一个service 用于批量插入
        try {
            saveBatch(tags);
        } catch (Exception e) {
            log.warn("该标签已存在", e);
        }
        return Result.ok();
    }

    /**
     * 查询标签分页查询
     */
    @Override
    public PageResponse findTagList(FindTagPageListReqVO findTagPageListReqVO) {

        // 获取参数
        Long current = findTagPageListReqVO.getCurrent();
        Long size = findTagPageListReqVO.getSize();
        LocalDate startDate = findTagPageListReqVO.getStartDate();
        LocalDate endDate = findTagPageListReqVO.getEndDate();
        String name = findTagPageListReqVO.getName();

        // 进行查询
        Page<TagDO> tagDOPage = tagMapper.selectPageList(current, size, startDate, endDate, name);

        // 获取数据
        List<TagDO> records = tagDOPage.getRecords();

        List<FindTagPageListRspVO> vos = null;

        // 进行转换
        if (!CollectionUtils.isEmpty(records)) {
            vos = records.stream()
                    .map(tagDO -> FindTagPageListRspVO
                            .builder()
                            .name(tagDO.getName())
                            .startDate(tagDO.getCreateTime())
                            .id(tagDO.getId()).build())
                    .collect(Collectors.toList());
        }

        // 返回
        return PageResponse.success(tagDOPage, vos);
    }

    /**
     * 删除标签
     */
    @Override
    public Result deleteTag(DeleteTagReqVO deleteTagReqVO) {

        Long tagId = deleteTagReqVO.getId();

        // 判断有无此id
        ArticleTagRelDO articleTagRelDO = articleTagRelMapper.selectOneByTagId(tagId);
        if (Objects.nonNull(articleTagRelDO)) {
            log.warn("==> 该标签下有文章，无法删除, tagId: {}", tagId);
            throw new BusinessException(ResultCodeEnum.TAG_CAN_NOT_DELETE);
        }

        tagMapper.deleteById(tagId);
        return Result.ok();
    }

    /**
     * 查询select标签
     */
    @Override
    public Result findTagSelectList(SearchTagReqVO searchTagReqVO) {

        String key = searchTagReqVO.getKey();

        // 执行模糊查询
        List<TagDO> tagDOS = tagMapper.selectByKey(key);

        List<SelectRspVO> selectRspVOS = null;

        // 如果列表不为空 就执行
        if (!CollectionUtils.isEmpty(tagDOS)) {
            selectRspVOS = tagDOS.stream()
                    .map(tagDO -> SelectRspVO
                            .builder()
                            .label(tagDO.getName())
                            .value(tagDO.getName()).build())
                    .collect(Collectors.toList());
        }
        return Result.ok(selectRspVOS);
    }

    /**
     * 获取所有下拉select
     */
    @Override
    public Result findTagSearch() {
        List<TagDO> tagDOS = tagMapper.selectList(null);

        // do -> vo
        List<SelectRspVO> vos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(tagDOS)) {
            tagDOS.forEach(tagDO -> {
                SelectRspVO selectRspVO = SelectRspVO.builder()
                        .label(tagDO.getName()).value(tagDO.getId())
                        .build();
                vos.add(selectRspVO);
            });
        }
        return Result.ok(vos);
    }
}
