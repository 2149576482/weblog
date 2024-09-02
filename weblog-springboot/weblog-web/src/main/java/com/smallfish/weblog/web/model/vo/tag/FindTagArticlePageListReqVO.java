package com.smallfish.weblog.web.model.vo.tag;

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
public class FindTagArticlePageListReqVO extends BasePageQuery {

    /**
     * 标签id
     */
    @NotNull(message = "标签id不能为空")
    private Long id;

}
