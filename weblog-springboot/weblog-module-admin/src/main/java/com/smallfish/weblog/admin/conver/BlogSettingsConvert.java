package com.smallfish.weblog.admin.conver;

import com.smallfish.weblog.admin.model.vo.blogsettings.FindBlogSettingsRspVO;
import com.smallfish.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.smallfish.weblog.common.domain.dos.BlogSettingsDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 实体转换
 **/
@Mapper
public interface BlogSettingsConvert {

    BlogSettingsConvert INSTANCE = Mappers.getMapper(BlogSettingsConvert.class);

    /**
     * 将 UpdateBlogSettingsReqVO 转化为 BlogSettingsDO
     */
    BlogSettingsDO convertVOToDO(UpdateBlogSettingsReqVO updateBlogSettingsReqVO);

    /**
     * DO > VO
     */
    FindBlogSettingsRspVO convertDOToVO(BlogSettingsDO blogSettingsDO);

}
