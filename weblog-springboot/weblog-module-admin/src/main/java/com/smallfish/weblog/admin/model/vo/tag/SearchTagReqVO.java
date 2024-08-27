package com.smallfish.weblog.admin.model.vo.tag;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author ArnanZZ
 * @version 1.0
 * @description: 标签接口VO
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(value = "查询 select 下拉列表标签")
public class SearchTagReqVO {

    @NotBlank(message = "标签名字不能为空")
    private String key;

}
