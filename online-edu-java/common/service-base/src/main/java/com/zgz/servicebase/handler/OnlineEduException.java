package com.zgz.servicebase.handler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author willie
 * @date 2021/10/5
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineEduException extends RuntimeException{

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "错误信息")
    private String msg;
}
