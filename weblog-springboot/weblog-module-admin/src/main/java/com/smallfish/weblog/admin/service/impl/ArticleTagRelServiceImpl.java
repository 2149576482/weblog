package com.smallfish.weblog.admin.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smallfish.weblog.common.domain.dos.ArticleTagRelDO;
import com.smallfish.weblog.common.domain.mapper.ArticleTagRelMapper;
import com.smallfish.weblog.admin.service.ArticleTagRelService;
@Service
public class ArticleTagRelServiceImpl extends ServiceImpl<ArticleTagRelMapper, ArticleTagRelDO> implements ArticleTagRelService {

}
