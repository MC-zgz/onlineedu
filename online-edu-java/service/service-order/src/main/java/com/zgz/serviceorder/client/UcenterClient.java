package com.zgz.serviceorder.client;

import com.zgz.commonutils.vo.MemberForOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author willie
 * @date 2021/12/7
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //跨模块根据memberId获取用户信息
    @GetMapping("/ucenter/member/getUcenterForOrder/{memberId}")
    public MemberForOrder getUcenterForOrder(@PathVariable("memberId") String memberId);
}
