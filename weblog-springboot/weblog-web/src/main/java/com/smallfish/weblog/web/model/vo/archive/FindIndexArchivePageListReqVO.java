package com.smallfish.weblog.web.model.vo.archive;

import com.smallfish.weblog.common.model.BasePageQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description:
 **/
@Data
@Builder
@ApiModel("归档分页 入参 ")
public class FindIndexArchivePageListReqVO extends BasePageQuery {
}
