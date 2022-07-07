package com.zgz.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zgz.eduservice.pojo.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zgz.eduservice.pojo.vo.CourseInfoForm;
import com.zgz.eduservice.pojo.vo.CoursePublishVo;
import com.zgz.eduservice.pojo.vo.CourseQueryVo;
import com.zgz.eduservice.pojo.vo.CourseWebVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author willie
 * @since 2021-11-13
 */
public interface EduCourseService extends IService<EduCourse> {

    String addCourseInfo(CourseInfoForm courseInfoForm);

    CourseInfoForm getCourseInfoById(String id);

    void updateCourseInfo(CourseInfoForm courseInfoForm);

    CoursePublishVo getCoursePublishById(String id);

    void delCourseInfo(String id);

    Map<String, Object> getCourseApiPageVo(Page<EduCourse> page, CourseQueryVo courseQueryVo);

    CourseWebVo getCourseWebInfo(String courseId);
}
