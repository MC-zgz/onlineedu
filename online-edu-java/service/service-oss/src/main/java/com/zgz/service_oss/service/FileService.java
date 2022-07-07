package com.zgz.service_oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author willie
 * @date 2021/11/7
 */
public interface FileService {
    String uploadFileOss(MultipartFile file);
}
