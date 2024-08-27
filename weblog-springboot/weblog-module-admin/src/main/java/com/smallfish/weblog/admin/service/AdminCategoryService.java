package com.smallfish.weblog.admin.service;

import com.smallfish.weblog.admin.model.vo.category.FindCategoryPageListReqVO;
import com.smallfish.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.smallfish.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.smallfish.weblog.common.utils.PageResponse;
import com.smallfish.weblog.common.utils.Result;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
public interface AdminCategoryService {

    /**
     * 添加分类
     */
    Result addCategory(AddCategoryReqVO addCategoryReqVO);

    /**
     * 分页查询分类
     */
    PageResponse findCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO);

    /**
     * 删除分类
     */
    Result deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO);

    /**
     * 查询分类
     */
    Result findCategorySelectList();


}
