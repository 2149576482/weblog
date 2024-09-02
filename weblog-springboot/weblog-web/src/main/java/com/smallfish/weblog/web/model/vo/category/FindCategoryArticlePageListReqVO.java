package com.smallfish.weblog.web.model.vo.category;

import com.smallfish.weblog.common.model.BasePageQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FindCategoryArticlePageListReqVO extends BasePageQuery {

    /**
     * 分类id
     */
    @NotNull(message = "分类id不能为空")
    private Long id;

}
