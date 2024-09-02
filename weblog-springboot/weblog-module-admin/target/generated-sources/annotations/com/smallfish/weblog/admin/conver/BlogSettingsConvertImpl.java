package com.smallfish.weblog.admin.conver;

import com.smallfish.weblog.admin.model.vo.blogsettings.FindBlogSettingsRspVO;
import com.smallfish.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.smallfish.weblog.common.domain.dos.BlogSettingsDO;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-02T14:59:48+0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 1.8.0_251 (Oracle Corporation)"
)
public class BlogSettingsConvertImpl implements BlogSettingsConvert {

    @Override
    public BlogSettingsDO convertVOToDO(UpdateBlogSettingsReqVO updateBlogSettingsReqVO) {
        if ( updateBlogSettingsReqVO == null ) {
            return null;
        }

        BlogSettingsDO.BlogSettingsDOBuilder blogSettingsDO = BlogSettingsDO.builder();

        blogSettingsDO.logo( updateBlogSettingsReqVO.getLogo() );
        blogSettingsDO.name( updateBlogSettingsReqVO.getName() );
        blogSettingsDO.author( updateBlogSettingsReqVO.getAuthor() );
        blogSettingsDO.introduction( updateBlogSettingsReqVO.getIntroduction() );
        blogSettingsDO.avatar( updateBlogSettingsReqVO.getAvatar() );
        blogSettingsDO.githubHomepage( updateBlogSettingsReqVO.getGithubHomepage() );
        blogSettingsDO.csdnHomepage( updateBlogSettingsReqVO.getCsdnHomepage() );
        blogSettingsDO.giteeHomepage( updateBlogSettingsReqVO.getGiteeHomepage() );
        blogSettingsDO.zhihuHomepage( updateBlogSettingsReqVO.getZhihuHomepage() );

        return blogSettingsDO.build();
    }

    @Override
    public FindBlogSettingsRspVO convertDOToVO(BlogSettingsDO blogSettingsDO) {
        if ( blogSettingsDO == null ) {
            return null;
        }

        FindBlogSettingsRspVO.FindBlogSettingsRspVOBuilder findBlogSettingsRspVO = FindBlogSettingsRspVO.builder();

        findBlogSettingsRspVO.logo( blogSettingsDO.getLogo() );
        findBlogSettingsRspVO.name( blogSettingsDO.getName() );
        findBlogSettingsRspVO.author( blogSettingsDO.getAuthor() );
        findBlogSettingsRspVO.introduction( blogSettingsDO.getIntroduction() );
        findBlogSettingsRspVO.avatar( blogSettingsDO.getAvatar() );
        findBlogSettingsRspVO.githubHomepage( blogSettingsDO.getGithubHomepage() );
        findBlogSettingsRspVO.csdnHomepage( blogSettingsDO.getCsdnHomepage() );
        findBlogSettingsRspVO.giteeHomepage( blogSettingsDO.getGiteeHomepage() );
        findBlogSettingsRspVO.zhihuHomepage( blogSettingsDO.getZhihuHomepage() );

        return findBlogSettingsRspVO.build();
    }
}
