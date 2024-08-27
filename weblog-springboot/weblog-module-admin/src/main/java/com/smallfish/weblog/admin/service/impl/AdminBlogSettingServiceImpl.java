package com.smallfish.weblog.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smallfish.weblog.admin.conver.BlogSettingsConvert;
import com.smallfish.weblog.admin.model.vo.blogsettings.FindBlogSettingsRspVO;
import com.smallfish.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.smallfish.weblog.admin.service.AdminBlogSettingService;
import com.smallfish.weblog.common.domain.dos.BlogSettingsDO;
import com.smallfish.weblog.common.domain.mapper.BlogSettingsMapper;
import com.smallfish.weblog.common.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Service
public class AdminBlogSettingServiceImpl extends ServiceImpl<BlogSettingsMapper, BlogSettingsDO> implements AdminBlogSettingService {

    @Resource
    private BlogSettingsMapper blogSettingsMapper;

    /**
     * 修改博客基础信息
     */
    @Override
    public Result updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {

        // vo转do
        BlogSettingsDO blogSettingsDO = BlogSettingsConvert.INSTANCE.convertVOToDO(updateBlogSettingsReqVO);
        blogSettingsDO.setId(1L);

        // 保存或更新 (当数据库中存在 id 为 1 的记录时  则执行更新 否则执行插入)
        saveOrUpdate(blogSettingsDO);
        return Result.ok();
    }

    /**
     * 获取博客基础信息
     */
    @Override
    public Result findDetail() {
        BlogSettingsDO blogSettingsDO = blogSettingsMapper.selectById(1L);
        FindBlogSettingsRspVO findBlogSettingsRspVO = BlogSettingsConvert.INSTANCE.convertDOToVO(blogSettingsDO);
        return Result.ok(findBlogSettingsRspVO);
    }
}
