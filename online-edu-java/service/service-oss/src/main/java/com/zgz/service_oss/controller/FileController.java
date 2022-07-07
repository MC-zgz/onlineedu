package com.zgz.service_oss.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zgz.commonutils.Result;
import com.zgz.service_oss.service.FileService;
import com.zgz.service_oss.service.impl.FileServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author willie
 * @date 2021/11/7
 */
@Api(description = "文件管理")
@RestController
@RequestMapping("/ossservice/fileoss")
@CrossOrigin
public class FileController {
    @Autowired
    private FileService fileServiceImpl;

    @ApiOperation("上传文件")
    @PostMapping("/uploadFile")
    public Result uploadFile(MultipartFile file) {
        //1.获取文件
        //2.调用接口上传文件，

        String url = fileServiceImpl.uploadFileOss(file);
        System.out.println(url);

        return Result.succ().message("文件上传成功").data("url", url);
    }
}
