package com.smallfish.weblog.web.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smallfish.weblog.common.domain.dos.ArticleDO;
import com.smallfish.weblog.common.domain.mapper.*;
import com.smallfish.weblog.common.utils.PageResponse;
import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.search.config.LuceneProperties;
import com.smallfish.weblog.web.convert.ArticleConvert;
import com.smallfish.weblog.web.model.vo.archive.FindArchiveArticleRspVO;
import com.smallfish.weblog.web.model.vo.archive.FindIndexArchivePageListReqVO;
import com.smallfish.weblog.web.model.vo.archive.FindIndexArchivePageListRspVO;
import com.smallfish.weblog.web.service.ArchiveService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Service
public class ArchiveServiceImpl implements ArchiveService {

    @Resource
    private ArticleMapper articleMapper;

    /**
     * 归档数据分页查询
     */
    @Override
    public Result findArchiveList(FindIndexArchivePageListReqVO findIndexArchivePageListReqVO) {

        Long current = findIndexArchivePageListReqVO.getCurrent();
        Long size = findIndexArchivePageListReqVO.getSize();
        Page<ArticleDO> articleDOPage = articleMapper.selectPageList(current, size, null, null, null);

        // 获取到分页列表数据
        List<ArticleDO> articleDOList = articleDOPage.getRecords();

        List<FindIndexArchivePageListRspVO> findArchiveArticleRspVOList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(articleDOList)) {
            List<FindArchiveArticleRspVO> collect = articleDOList.stream().map(ArticleConvert.INSTANCE::articleToVo).collect(Collectors.toList());
            // 按照月份分组
            Map<YearMonth, List<FindArchiveArticleRspVO>> map = collect.stream().collect(Collectors.groupingBy(FindArchiveArticleRspVO::getCreateMonth));

            // 进行倒序分组 按照月份
            Map<YearMonth, List<FindArchiveArticleRspVO>> sortedMap = new TreeMap<>(Collections.reverseOrder());
            sortedMap.putAll(map);

            // 遍历排序之后的map 将其转换为归档 vo
            sortedMap.forEach((k, v) -> findArchiveArticleRspVOList.add(FindIndexArchivePageListRspVO.builder().articles(v).month(k).build()));

        }
        return PageResponse.success(articleDOPage, findArchiveArticleRspVOList);
    }
}






















