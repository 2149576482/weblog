package com.smallfish.weblog.common.domain.dos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePublishCountDO {

    private LocalDate date;
    private Long count;

}
