package com.zgz.serviceorder.client;

import com.zgz.commonutils.vo.CourseWebVoForOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author willie
 * @date 2021/12/6
 */
@Component
@FeignClient("service-edu")
public interface EduClient {
    //跨模块根据课程id查询课程相关信息
    @GetMapping("/eduservice/courseapi/getCourseInfoForOrder/{courseId}")
    public CourseWebVoForOrder getCourseInfoForOrder(@PathVariable("courseId") String courseId);
}
