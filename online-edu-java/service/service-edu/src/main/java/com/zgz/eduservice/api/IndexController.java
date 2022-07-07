package com.zgz.eduservice.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgz.commonutils.Result;
import com.zgz.eduservice.pojo.EduCourse;
import com.zgz.eduservice.pojo.EduTeacher;
import com.zgz.eduservice.service.EduCourseService;
import com.zgz.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author willie
 * @date 2021/11/29
 */
@Api(description = "首页显示")
@RestController
@RequestMapping("/eduservice/index")
@CrossOrigin
public class IndexController {
    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "首页展示课程信息、讲师信息")
    @GetMapping("/getCourseAndTeacherList")
    public Result getCourseAndTeacherList(){
        //课程信息
        QueryWrapper<EduCourse> courseWrapper = new QueryWrapper<>();
        courseWrapper.orderByDesc("gmt_create");
        courseWrapper.eq("status","Normal");
        courseWrapper.last("limit 8");
        List<EduCourse> courseList = eduCourseService.list(courseWrapper);
        //讲师信息
        QueryWrapper<EduTeacher> teacherWrapper = new QueryWrapper<>();
        teacherWrapper.orderByDesc("gmt_create");
        teacherWrapper.last("limit 4");
        List<EduTeacher> teacherList = eduTeacherService.list(teacherWrapper);
        return Result.succ().data("courseList",courseList).data("teacherList",teacherList);

    }
}
