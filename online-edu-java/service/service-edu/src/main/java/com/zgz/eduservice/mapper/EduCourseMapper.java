package com.zgz.eduservice.mapper;

import com.zgz.eduservice.pojo.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zgz.eduservice.pojo.vo.CoursePublishVo;
import com.zgz.eduservice.pojo.vo.CourseWebVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author willie
 * @since 2021-11-13
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getCoursePublishById(String id);

    CourseWebVo getCourseWebInfo(String courseId);
}
