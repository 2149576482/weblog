package com.smallfish.weblog.common.model;

import lombok.Data;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 分页请求基础类
 **/
@Data
public class BasePageQuery {

    /**
     * 当前页码
     */
    private Long current = 1L;

    /**
     * 每页展示多少
     */
    private Long size = 10L;

}
