package com.zgz.servicevod.controller;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.zgz.commonutils.Result;
import com.zgz.servicebase.handler.OnlineEduException;
import com.zgz.servicevod.utils.AliyunVodSDKUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author willie
 * @date 2021/11/27
 */
@Api(description = "视频管理")
@RestController
@RequestMapping("/vodservice/vod")
@CrossOrigin
public class VideoAdminController {

    String accessKeyId = "LTAI5gsfgax1r8XUYg";
    String accessKeySecret = "2WCEfasdfafaECjO8qPFwSCW4S";

    @ApiOperation(value = "上传视频")
    @PostMapping("/uploadVideo")
    public Result uploadVideo(MultipartFile file){

        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String title = originalFilename.substring(0, originalFilename.lastIndexOf("."));

            UploadStreamRequest request = new UploadStreamRequest(accessKeyId,accessKeySecret,
                    title,originalFilename,inputStream);

            request.setApiRegionId("cn-shenzhen");

            UploadVideoImpl uploadVideo = new UploadVideoImpl();
            UploadStreamResponse response = uploadVideo.uploadStream(request);

            System.out.println("上传成功！");
            String videoId = response.getVideoId();
            return Result.succ().data("videoId",videoId);

        } catch (IOException e) {
            e.printStackTrace();
            throw new OnlineEduException(20001,"上传视频失败！");
        }
    }

    @ApiOperation(value = "删除视频")
    @DeleteMapping("/delVideo/{videoId}")
    public Result delVideo(@PathVariable("videoId") String videoId){
        try {
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKeyId,accessKeySecret);

            //*创建请求对象（不同操作，类不同）
            DeleteVideoRequest request = new DeleteVideoRequest();
            //支持传入多个视频ID，多个用逗号分隔
            request.setVideoIds(videoId);

            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.println("删除成功！");
            return Result.succ();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new OnlineEduException(20001,"删除视频失败！");
        }

    }

    @ApiOperation(value = "批量删除视频")
    @DeleteMapping("/delVideoList")
    public Result delVideoList(@RequestParam("videoIdList") List<String> videoIdList){
        try {
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKeyId,accessKeySecret);

            //*创建请求对象（不同操作，类不同）
            DeleteVideoRequest request = new DeleteVideoRequest();
            //支持传入多个视频ID，多个用逗号分隔
            String videoIds = StringUtils.join(videoIdList.toArray(),",");
            request.setVideoIds(videoIds);

            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.println("批量删除成功！");
            return Result.succ();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new OnlineEduException(20001,"删除视频失败！");
        }

    }

    @ApiOperation(value = "根据视频id获取视频播放凭证")
    @GetMapping("/getPlayAuth/{vid}")
    public Result getPlayAuth(@PathVariable String vid){
        try {
            //（1）创建初始化对象
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(accessKeyId,accessKeySecret);
            //（2）创建request、response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
            //（3）向request设置视频id
            request.setVideoId(vid);
            //播放凭证有过期时间，默认值：100秒 。取值范围：100~3000。
            //request.setAuthInfoTimeout(200L);
            //（4）调用初始化方法实现功能
            response = client.getAcsResponse(request);
            //（5）调用方法返回response对象，获取内容
            String playAuth = response.getPlayAuth();
            System.out.println(playAuth);
            return Result.succ().data("playAuth",playAuth);
        } catch (ClientException e) {
            throw new OnlineEduException(20001,"获取视频凭证失败");
        }
    }
}
