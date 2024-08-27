package com.smallfish.weblog.admin.service;

import com.smallfish.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.smallfish.weblog.common.utils.Result;

public interface AdminBlogSettingService {

    /**
     * 修改博客基础信息
     */
    Result updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO);

    /**
     * 获取博客基础信息
     */
    Result findDetail();
}
