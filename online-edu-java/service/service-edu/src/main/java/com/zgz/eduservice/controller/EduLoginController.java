package com.zgz.eduservice.controller;

import com.zgz.commonutils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

/**
 * @author willie
 * @date 2021/10/28
 */
@Api(description = "模拟登录")
@RestController
@RequestMapping("/eduUser")
@CrossOrigin
public class EduLoginController {
    @ApiOperation("模拟登录")
    @PostMapping("/login")
    public Result login(){
        return Result.succ()
                .data("username","admin")
                .data("password","admin")
                .data("token","admin");
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/getUserInfo")
    public Result userInfo(){
        return Result.succ()
                .data("roles","admin")
                .data("name","战三")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
