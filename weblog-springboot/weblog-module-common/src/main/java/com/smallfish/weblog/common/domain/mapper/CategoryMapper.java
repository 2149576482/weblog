package com.smallfish.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smallfish.weblog.common.domain.dos.CategoryDO;

public interface CategoryMapper extends BaseMapper<CategoryDO> {

    /**
     * 根据分类名查询
     */
    default CategoryDO selectByName(String categoryName) {
        // 构建查询条件
        LambdaUpdateWrapper<CategoryDO> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(CategoryDO::getName, categoryName);
        return selectOne(wrapper);
    }

}
