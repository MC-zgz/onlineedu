package com.zgz.service_oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zgz.service_oss.service.FileService;
import com.zgz.service_oss.utils.ConstantPropertiesUtils;
import com.zgz.servicebase.handler.GlobalExceptionHandler;
import com.zgz.servicebase.handler.OnlineEduException;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author willie
 * @date 2021/11/7
 */
@Service
public class FileServiceImpl implements FileService {
    //上传文件
    @Override
    public String uploadFileOss(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        String filename = file.getOriginalFilename();
        System.out.println(filename);
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


        try {
            //上传文件流
            InputStream inputStream = file.getInputStream();
//            InputStream inputStream = new FileInputStream("D:\\zgz_work\\imgage1.jpg");
            //优化文件名
            filename = UUID.randomUUID().toString()+filename;
            //优化文件存储路径
            String path = new DateTime().toString("yyyy/MM/dd");
            filename = path+"/"+filename;
            System.out.println("filename" + filename);

            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName, filename, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            //"https://zgz-online-education-file.oss-cn-guangzhou.aliyuncs.com/imgage1.jpg"
            String url = "https://" + bucketName + "." + endpoint + "/" +filename;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            throw new OnlineEduException(20001,"上传失败");
        }
    }
}
