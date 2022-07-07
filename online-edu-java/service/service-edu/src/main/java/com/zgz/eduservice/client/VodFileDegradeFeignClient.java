package com.zgz.eduservice.client;

import com.zgz.commonutils.Result;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author willie
 * @date 2021/11/29
 */
@Component
public class VodFileDegradeFeignClient implements VideoClient {
    @Override
    public Result delVideo(String videoId) {
        return Result.error().message("删除失败！");
    }

    @Override
    public Result delVideoList(List<String> videoIdList) {
        return Result.error().message("删除失败！");
    }
}
