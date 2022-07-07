package com.zgz.ucenter.pojo.Vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author willie
 * @date 2021/12/1
 */
@Data
public class RegisterVo {
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

//    @ApiModelProperty(value = "验证码")
//    private String code;

}
