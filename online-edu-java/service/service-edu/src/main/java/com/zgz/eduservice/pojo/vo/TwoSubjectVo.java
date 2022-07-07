package com.zgz.eduservice.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author willie
 * @date 2021/11/13
 */
@Data
public class TwoSubjectVo {
    @ApiModelProperty(value = "课程分类id")
    private String id;

    @ApiModelProperty(value = "课程分类名称")
    private String title;
}
