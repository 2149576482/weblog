package com.smallfish.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.smallfish.weblog.common.domain.dos.TagDO;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


public interface TagMapper extends BaseMapper<TagDO> {

    /**
     * 进行分页查询
     * 返回Page<TagDO>
     */
    default Page<TagDO> selectPageList(long current, long size, LocalDate startDate, LocalDate endDate, String name) {

        Page<TagDO> page = new Page<>(current, size);

        // 构造查询条件
        LambdaQueryWrapper<TagDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Objects.nonNull(name), TagDO::getName, name)
                .ge(Objects.nonNull(startDate), TagDO::getCreateTime, startDate)
                .le(Objects.nonNull(endDate), TagDO::getCreateTime, endDate)
                .orderByDesc(TagDO::getCreateTime);

        Page<TagDO> tagDOPage = selectPage(page, wrapper);
        return tagDOPage;
    }

    /**
     * 根据名称进行模糊查询
     */
    default List<TagDO> selectByKey(String key) {
        LambdaQueryWrapper<TagDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(!Strings.isBlank(key), TagDO::getName, key)
                .orderByDesc(TagDO::getCreateTime);
        return selectList(wrapper);
    }
}
