package com.zgz.smsservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.zgz.smsservice.service.SmsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author willie
 * @date 2021/11/30
 */
@Service
public class SmsServiceImpl implements SmsService {
    //发送短信
    @Override
    public boolean sendSmsPhone(String phone, Map<String, String> param) {
        if(StringUtils.isEmpty(phone)) return false;

        DefaultProfile profile =
                DefaultProfile.getProfile("default", "afdafafafaf123414", "2525wsdfafasdq214qdad");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("XXXXXXX");
        request.setVersion("2017-05-25");
        request.setAction("XXXXXX");

        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "XXXXXXXX");//审核通过签名
        request.putQueryParameter("TemplateCode", "XXXXXXX0");//审核通过模板
        request.putQueryParameter("TemplateParam", JSONObject.toJSONString(param));

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
