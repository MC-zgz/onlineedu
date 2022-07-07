package com.zgz.smsservice.service;

import java.util.Map;

/**
 * @author willie
 * @date 2021/11/30
 */
public interface SmsService {
    boolean sendSmsPhone(String phone, Map<String, String> param);
}
