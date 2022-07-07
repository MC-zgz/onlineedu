package com.zgz.eduservice.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zgz.commonutils.Result;
import com.zgz.eduservice.pojo.EduCourse;
import com.zgz.eduservice.pojo.EduTeacher;
import com.zgz.eduservice.service.EduCourseService;
import com.zgz.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author willie
 * @date 2021/12/3
 */
@Api(description = "前台讲师展示")
@RestController
@RequestMapping("/eduservice/teacherapi")
@CrossOrigin
public class TeacherApiController {
    @Autowired
    private EduTeacherService eduTeacherService;
    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation("分页查询讲师列表")
    @GetMapping("/getTeacherApiByPage/{current}/{limit}")
    public Result getTeacherApiByPage(@PathVariable Long current, @PathVariable Long limit){
        Page<EduTeacher> page = new Page<>(current,limit);
        Map<String,Object> map =  eduTeacherService.getTeacherApiByPage(page);
        return Result.succ().data(map);
    }

    @ApiOperation("前台名师详情")
    @GetMapping("/getTeacherCourseById/{id}")
    public Result getTeacherCourseById(@PathVariable String id){

        //1讲师信息详情
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        //2 相关课程
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("teacher_id",id);
        List<EduCourse> courseList = eduCourseService.list(queryWrapper);


        return Result.succ().data("eduTeacher",eduTeacher).data("courseList",courseList);
    }
}
