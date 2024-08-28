package com.smallfish.weblog.web.convert;

import com.smallfish.weblog.common.domain.dos.BlogSettingsDO;
import com.smallfish.weblog.web.model.vo.blogsettings.FindBlogSettingsListRspVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Mapper
public interface BlogSettingsConvert {

    BlogSettingsConvert INSTANCE = Mappers.getMapper(BlogSettingsConvert.class);

    // do -> vo
    FindBlogSettingsListRspVO blogSettingsListRspVO(BlogSettingsDO settingsDO);

}
