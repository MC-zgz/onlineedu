package com.zgz.ucenter.controller;


import com.zgz.commonutils.Result;
import com.zgz.commonutils.utils.JwtUtils;
import com.zgz.commonutils.vo.MemberForOrder;
import com.zgz.ucenter.pojo.Member;
import com.zgz.ucenter.pojo.Vo.LoginVo;
import com.zgz.ucenter.pojo.Vo.RegisterVo;
import com.zgz.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author willie
 * @since 2021-11-30
 */
@Api(description="前台用户会员管理")
@RestController
@RequestMapping("/ucenter/member")
@CrossOrigin
public class MemberController {

    @Autowired
    MemberService memberService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return Result.succ();
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginVo loginVo){
        String token = memberService.login(loginVo);
        return Result.succ().data("token",token);
    }

    @ApiOperation(value = "根据token字符串获取用户信息")
    @GetMapping("/getUcenterByToken")
    public Result getUcenterByToken(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Member member = memberService.getById(memberId);
        return Result.succ().data("member",member);
    }

    @ApiOperation(value = "跨模块根据memberId获取用户信息")
    @GetMapping("/getUcenterForOrder/{memberId}")
    public MemberForOrder getUcenterForOrder(@PathVariable("memberId") String memberId){
        Member member = memberService.getById(memberId);
        MemberForOrder memberForOrder = new MemberForOrder();
        BeanUtils.copyProperties(member,memberForOrder);
        return memberForOrder;
    }

    @ApiOperation(value = "统计注册人数远程调用")
    @GetMapping("/countRegister/{day}")
    public Result countRegister(@PathVariable String day){
        Integer count = memberService.countRegister(day);
        return Result.succ().data("countRegister",count);
    }


}

