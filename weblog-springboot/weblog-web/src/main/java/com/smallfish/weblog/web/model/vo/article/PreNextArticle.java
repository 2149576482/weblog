package com.smallfish.weblog.web.model.vo.article;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PreNextArticle {

    private Long articleId;
    private String articleTitle;

}
