package com.zgz.eduservice.controller;


import com.zgz.commonutils.Result;
import com.zgz.eduservice.pojo.EduCourse;
import com.zgz.eduservice.pojo.vo.CourseInfoForm;
import com.zgz.eduservice.pojo.vo.CoursePublishVo;
import com.zgz.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author willie
 * @since 2021-11-13
 */
@Api(description = "课程管理")
@RestController
@RequestMapping("/eduservice/edu-course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation(value = "添加课程信息")
    @PostMapping("/addCourseInfo")
    public Result addCourseInfo(@RequestBody CourseInfoForm courseInfoForm){

        String courseId = eduCourseService.addCourseInfo(courseInfoForm);

        return Result.succ().data("courseId",courseId);
    }

    @ApiOperation(value = "根据ID查询课程信息")
    @GetMapping("/getCourseInfoById/{id}")
    public Result getCourseInfoById(@PathVariable String id){

        CourseInfoForm courseInfoForm =  eduCourseService.getCourseInfoById(id);

        return Result.succ().data("courseInfoForm",courseInfoForm);
    }

    @ApiOperation(value = "修改课程信息")
    @PostMapping("/updateCourseInfo")
    public Result updateCourseInfo(@RequestBody CourseInfoForm courseInfoForm){

        eduCourseService.updateCourseInfo(courseInfoForm);

        return Result.succ();
    }
    @ApiOperation(value = "根据课程id查询课程发布信息")
    @GetMapping("/getCoursePublishById/{id}")
    public Result getCoursePublishById(@PathVariable String id){
        CoursePublishVo coursePublishVo = eduCourseService.getCoursePublishById(id);
        return Result.succ().data("coursePublishVo",coursePublishVo);
    }

    @ApiOperation(value = "根据课程id发布课程")
    @PostMapping("/publishCourse/{id}")
    public Result publishCourseById(@PathVariable String id){
        EduCourse eduCourse = eduCourseService.getById(id);
        eduCourse.setStatus("Normal");

        eduCourseService.updateById(eduCourse);
        return Result.succ();
    }

    @ApiOperation(value = "查询所有课程信息")
    @GetMapping("/getAllCourse")
    public Result getAllCourse(){
        List<EduCourse> list = eduCourseService.list(null);
        return Result.succ().data("list",list);
    }

    @ApiOperation(value = "根据id删除课程相关信息")
    @DeleteMapping("/delCourseInfo/{id}")
    public Result delCourseInfo(@PathVariable String id){
        eduCourseService.delCourseInfo(id);
        return Result.succ();
    }

}

