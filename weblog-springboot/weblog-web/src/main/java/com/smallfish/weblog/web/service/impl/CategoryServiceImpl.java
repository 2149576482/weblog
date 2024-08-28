package com.smallfish.weblog.web.service.impl;

import com.smallfish.weblog.common.domain.dos.CategoryDO;
import com.smallfish.weblog.common.domain.mapper.CategoryMapper;
import com.smallfish.weblog.common.utils.Result;
import com.smallfish.weblog.web.model.vo.category.FindCategoryListRspVO;
import com.smallfish.weblog.web.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 获取全部分类
     */
    @Override
    public Result findCategoryList() {
        List<CategoryDO> categoryDOList = categoryMapper.selectList(null);
        List<FindCategoryListRspVO> findCategoryListRspVOS = new ArrayList<>();
        categoryDOList.forEach(categoryDO -> {
            FindCategoryListRspVO findCategoryListRspVO = FindCategoryListRspVO
                    .builder()
                    .id(categoryDO.getId())
                    .name(categoryDO.getName())
                    .build();
            findCategoryListRspVOS.add(findCategoryListRspVO);
        });
        return Result.ok(findCategoryListRspVOS);
    }
}
