package com.smallfish.weblog.web.service.impl;

import com.smallfish.weblog.common.domain.dos.BlogSettingsDO;
import com.smallfish.weblog.common.domain.mapper.BlogSettingsMapper;
import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.convert.BlogSettingsConvert;
import com.smallfish.weblog.web.model.vo.blogsettings.FindBlogSettingsListRspVO;
import com.smallfish.weblog.web.service.BlogSettingsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Service
public class BlogSettingsServiceImpl implements BlogSettingsService {

    @Resource
    private BlogSettingsMapper blogSettingsMapper;
    /**
     * 获取文章首页博客设置
     */
    @Override
    public Result findDetail() {

        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1L);
        FindBlogSettingsListRspVO findBlogSettingsListRspVO = BlogSettingsConvert.INSTANCE.blogSettingsListRspVO(blogSettingsDO);
        return Result.ok(findBlogSettingsListRspVO);

    }
}
