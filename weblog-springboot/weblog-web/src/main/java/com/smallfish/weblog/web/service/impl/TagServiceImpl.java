package com.smallfish.weblog.web.service.impl;

import com.smallfish.weblog.common.domain.dos.TagDO;
import com.smallfish.weblog.common.domain.mapper.TagMapper;
import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.model.vo.tag.FindTagListRspVO;
import com.smallfish.weblog.web.service.TagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Service
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;

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
}
