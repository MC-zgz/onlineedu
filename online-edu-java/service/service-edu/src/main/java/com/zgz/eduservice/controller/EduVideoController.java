package com.zgz.eduservice.controller;


import com.zgz.commonutils.Result;
import com.zgz.eduservice.client.VideoClient;
import com.zgz.eduservice.pojo.EduChapter;
import com.zgz.eduservice.pojo.EduVideo;
import com.zgz.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author willie
 * @since 2021-11-14
 */
@Api(description = "小节管理")
@RestController
@RequestMapping("/eduservice/edu-video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VideoClient videoClient;

    @ApiOperation(value = "添加小节")
    @PostMapping("/addVideo")
    public Result addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return Result.succ().data("eduVideo",eduVideo);
    }

    @ApiOperation(value = "根据Id删除小节")
    @DeleteMapping("/delVideo/{id}")
    // 删除小节需要同时删除小节视频（阿里云）
    public Result delVideo(@PathVariable String id){
        EduVideo eduVideo = eduVideoService.getById(id);
        String videoId = eduVideo.getVideoSourceId();
        if(videoId != null){
            videoClient.delVideo(videoId);
        }
        eduVideoService.removeById(id);
        return Result.succ();
    }

    @ApiOperation(value = "根据Id查询小节")
    @GetMapping("/getVideoById/{id}")
    public Result getVideoById(@PathVariable String id){
        EduVideo eduVideo = eduVideoService.getById(id);
        return Result.succ().data("eduVideo",eduVideo);
    }

    @ApiOperation(value = "根据Id修改章节")
    @PostMapping("/updVideo")
    public Result updVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateById(eduVideo);
        return Result.succ().data("eduVideo",eduVideo);
    }

}

