package com.zgz.eduservice.client;

import com.zgz.commonutils.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author willie
 * @date 2021/11/27
 */
@Component
@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
public interface VideoClient {
    //删除视频
    //请求URL必须为完整
    //参数注解 参数名不能省略
    @DeleteMapping("/vodservice/vod/delVideo/{videoId}")
    public Result delVideo(@PathVariable("videoId") String videoId);

    @DeleteMapping("/vodservice/vod/delVideoList")
    public Result delVideoList(@RequestParam("videoIdList") List<String> videoIdList);
}
