package com.zgz.eduservice.controller;


import com.zgz.commonutils.Result;
import com.zgz.eduservice.pojo.EduSubject;
import com.zgz.eduservice.pojo.vo.OneSubjectVo;
import com.zgz.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 *
 * @author willie
 * @since 2021-11-10
 */
@Api(description = "课程分类管理")
@RestController
@RequestMapping("/eduservice/edu-subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectServiceImpl;

    @ApiOperation("批量导入课程分类")
    @PostMapping("/addSubject")
    public Result addSubject(MultipartFile file){
        eduSubjectServiceImpl.addSubject(file,eduSubjectServiceImpl);
        return Result.succ();
    }

    @ApiOperation("查询所有课程分类")
    @GetMapping("/subjectList")
    public Result getAllSubject(){
        List<OneSubjectVo> allSubjectList =  eduSubjectServiceImpl.getAllSubject();
        return Result.succ().data("allSubjectList",allSubjectList);
    }
}

