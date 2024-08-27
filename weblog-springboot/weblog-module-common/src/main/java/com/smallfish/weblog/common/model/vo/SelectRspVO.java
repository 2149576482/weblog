package com.smallfish.weblog.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 通用的查询VO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectRspVO {

    /**
     * 下拉列表展示的文字
     */
    private String label;

    /**
     * 文字对应的值 ID
     */
    private Object value;

}
