package com.zgz.eduservice.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zgz.commonutils.Result;
import com.zgz.commonutils.vo.CourseWebVoForOrder;
import com.zgz.eduservice.pojo.EduCourse;
import com.zgz.eduservice.pojo.vo.ChapterVo;
import com.zgz.eduservice.pojo.vo.CourseQueryVo;
import com.zgz.eduservice.pojo.vo.CourseWebVo;
import com.zgz.eduservice.service.EduChapterService;
import com.zgz.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author willie
 * @date 2021/12/3
 */
@Api(description = "前台课程展示")
@RestController
@RequestMapping("/eduservice/courseapi")
@CrossOrigin
public class CourseApiController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;


    @ApiOperation(value = "带条件分页查询课程列表")
    @PostMapping("getCourseApiPageVo/{current}/{limit}")
    public Result getCourseApiPageVo(@PathVariable Long current,@PathVariable Long limit,@RequestBody CourseQueryVo courseQueryVo){

        Page<EduCourse> page = new Page<>(current,limit);
        Map<String,Object> map = eduCourseService.getCourseApiPageVo(page,courseQueryVo);

        return Result.succ().data(map);
    }

    @ApiOperation(value = "根据课程id查询课程相关信息")
    @GetMapping("getCourseWebInfo/{courseId}")
    public Result getCourseWebInfo(@PathVariable String courseId){
        //查询课程相关信息存入courseWebInfo
        CourseWebVo courseWebVo = eduCourseService.getCourseWebInfo(courseId);
        //查询课程大纲信息
        List<ChapterVo> chapterVideoList = eduChapterService.getChapterVideoById(courseId);

        return Result.succ().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList);
    }

    @ApiOperation(value = "跨模块根据课程id查询课程相关信息")
    @GetMapping("getCourseInfoForOrder/{courseId}")
    public CourseWebVoForOrder getCourseInfoForOrder(@PathVariable("courseId") String courseId){
        CourseWebVo courseWebInfo = eduCourseService.getCourseWebInfo(courseId);
        CourseWebVoForOrder courseWebVoForOrder = new CourseWebVoForOrder();
        BeanUtils.copyProperties(courseWebInfo,courseWebVoForOrder);
        return courseWebVoForOrder;
    }
}
