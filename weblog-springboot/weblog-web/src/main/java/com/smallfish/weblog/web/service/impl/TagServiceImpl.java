package com.smallfish.weblog.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smallfish.weblog.common.domain.dos.ArticleDO;
import com.smallfish.weblog.common.domain.dos.ArticleTagRelDO;
import com.smallfish.weblog.common.domain.dos.TagDO;
import com.smallfish.weblog.common.domain.mapper.ArticleMapper;
import com.smallfish.weblog.common.domain.mapper.ArticleTagRelMapper;
import com.smallfish.weblog.common.domain.mapper.TagMapper;
import com.smallfish.weblog.common.enums.ResultCodeEnum;
import com.smallfish.weblog.common.exception.BusinessException;
import com.smallfish.weblog.common.utils.PageResponse;
import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.convert.ArticleConvert;
import com.smallfish.weblog.web.model.vo.tag.FindTagArticlePageListReqVO;
import com.smallfish.weblog.web.model.vo.tag.FindTagArticlePageListRspVO;
import com.smallfish.weblog.web.model.vo.tag.FindTagListRspVO;
import com.smallfish.weblog.web.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Service
@Slf4j
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleTagRelMapper articleTagRelMapper;

    @Override
    public Result findTagList() {
        List<TagDO> tagDOS = tagMapper.selectList(null);
        List<FindTagListRspVO> findTagListRspVOList = new ArrayList<>();
        tagDOS.forEach(tagDO -> {
            FindTagListRspVO findTagListRspVO = FindTagListRspVO.builder()
                    .id(tagDO.getId()).name(tagDO.getName()).build();
            findTagListRspVOList.add(findTagListRspVO);
        });
        return Result.ok(findTagListRspVOList);
    }

    /**
     * 获取首页标签对应文章
     */
    @Override
    public Result selectListByTagId(FindTagArticlePageListReqVO findTagArticlePageListReqVO) {

        Long tagId = findTagArticlePageListReqVO.getId();
        Long current = findTagArticlePageListReqVO.getCurrent();
        Long size = findTagArticlePageListReqVO.getSize();

        // 根据标签id 查询标签
        TagDO tagDO = tagMapper.selectById(tagId);
        if (Objects.isNull(tagDO)) {
            log.warn("==> 标签不存在, tagId: {}", tagId);
            throw new BusinessException(ResultCodeEnum.TAG_NOT_EXISTED);
        }

        // 根据标签id 获取到所有的文章标签对应关系
        List<ArticleTagRelDO> articleDOList = articleTagRelMapper.selectListByTagId(tagId);

        // 如果该标签下没有文章
        if (CollectionUtils.isEmpty(articleDOList)) {
            log.warn("==> 该标签下没有文章, tagId: {}", tagId);
            return PageResponse.success(null, null);
        }

        // 获得所有文章ID
        List<Long> articleIds = articleDOList.stream().map(ArticleTagRelDO::getArticleId).collect(Collectors.toList());

        // 根据文章ID集合查询文章分页数据
        Page<ArticleDO> articleDOPage = articleMapper.selectPageListByArticleIds(current, size, articleIds);
        List<ArticleDO> articleDOS = articleDOPage.getRecords();

        List<FindTagArticlePageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(articleDOS)) {
            vos = articleDOS.stream()
                    .map(ArticleConvert.INSTANCE::tagArticleToVo)
                    .collect(Collectors.toList());
        }

        return PageResponse.success(articleDOPage, vos);
    }
}


















