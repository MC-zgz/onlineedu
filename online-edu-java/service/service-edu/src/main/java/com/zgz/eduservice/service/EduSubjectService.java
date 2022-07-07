package com.zgz.eduservice.service;

import com.zgz.eduservice.pojo.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zgz.eduservice.pojo.vo.OneSubjectVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author willie
 * @since 2021-11-10
 */
public interface EduSubjectService extends IService<EduSubject> {

    void addSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubjectVo> getAllSubject();
}
