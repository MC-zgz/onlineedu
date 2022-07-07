package com.zgz.servicestatistics.client;

import com.zgz.commonutils.Result;
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
    //统计注册人数远程调用
    @GetMapping("/ucenter/member/countRegister/{day}")
    public Result countRegister(@PathVariable String day);
}
