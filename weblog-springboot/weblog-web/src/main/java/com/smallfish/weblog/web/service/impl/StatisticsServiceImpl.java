package com.smallfish.weblog.web.service.impl;

import com.smallfish.weblog.common.domain.dos.ArticleDO;
import com.smallfish.weblog.common.domain.mapper.ArticleMapper;
import com.smallfish.weblog.common.domain.mapper.CategoryMapper;
import com.smallfish.weblog.common.domain.mapper.TagMapper;
import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.model.vo.dashboard.FindDashboardStatisticsInfoRspVO;
import com.smallfish.weblog.web.service.StatisticsService;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private TagMapper tagMapper;

    @Override
    public Result findInfo() {

        // 获取文章总数
        val articleCount = articleMapper.selectCount(null);

        // 获取标签总数
        Long tagCount = tagMapper.selectCount(null);

        // 获取分类总数
        Long categoryCount = categoryMapper.selectCount(null);

        // 获取总访问量
        List<ArticleDO> articleDOList = articleMapper.selectAllReadNum();

        Long pvCount = 0L;
        if (!CollectionUtils.isEmpty(articleDOList)) {
            pvCount = articleDOList.stream().mapToLong(ArticleDO::getReadNum).sum();
        }
        FindDashboardStatisticsInfoRspVO findDashboardStatisticsInfoRspVO = FindDashboardStatisticsInfoRspVO.builder()
                .articleTotalCount(articleCount)
                .categoryTotalCount(categoryCount)
                .tagTotalCount(tagCount)
                .pvTotalCount(pvCount)
                .build();
        return Result.ok(findDashboardStatisticsInfoRspVO);
    }
}
