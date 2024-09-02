package com.smallfish.weblog.web.model.vo.article;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FindArticleDetailReqVO {

    @NotNull(message = "文章ID不能为空")
    private Long articleId;

}
