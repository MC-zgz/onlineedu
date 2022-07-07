package com.zgz.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zgz.eduservice.pojo.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author willie
 * @since 2021-09-27
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> getTeacherApiByPage(Page<EduTeacher> page);
}
