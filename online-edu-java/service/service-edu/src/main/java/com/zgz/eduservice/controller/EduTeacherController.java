package com.zgz.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zgz.commonutils.Result;
import com.zgz.eduservice.pojo.EduTeacher;
import com.zgz.eduservice.pojo.vo.TeacherQuery;
import com.zgz.eduservice.service.EduTeacherService;
import com.zgz.servicebase.handler.OnlineEduException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author willie
 * @since 2021-09-27
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 查询全部讲师信息
     * @return
     */
    @ApiOperation("所有讲师列表")
    @GetMapping("/getAll")
    public Result getAllTeacher(){

        List<EduTeacher> list = eduTeacherService.list(null);
        return Result.succ().data("list",list);
    }

    /**
     * 根据id删除讲师
     * @param id
     * @return
     */
    @ApiOperation("删除讲师")
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable String id){
        boolean remove = eduTeacherService.removeById(id);
        if (remove){
            return Result.succ();
        }else {
            return Result.error();
        }
    }

    @ApiOperation("分页查询讲师列表")
    @GetMapping("/getTeacherByPage/{current}/{limit}")
    public Result getTeacherByPage(@PathVariable Long current,@PathVariable Long limit){
        Page<EduTeacher> page = new Page<>(current,limit);
        eduTeacherService.page(page,null);
        List<EduTeacher> records = page.getRecords();
        long total = page.getTotal();
        //1、存入map
        Map<String,Object> map = new HashMap<>();
        map.put("list",records);
        map.put("total",total);
        return Result.succ().data(map);
        //2、直接拼接
//        return Result.succ().data("list",records).data("total",total);
    }

    @ApiOperation("条件分页查询讲师列表")
    @PostMapping("/getTeacherByPageVo/{current}/{limit}")
    public Result getTeacherByPageVo
            (@PathVariable Long current,
             @PathVariable Long limit,
             @RequestBody TeacherQuery teacherQuery){
        //@RequestBody 把json穿转化成实体类

        //1取出查询条件
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        //2、判断条件是否为空，如果不为空则拼写sql
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }

        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.like("gmt_create",end);
        }

        Page<EduTeacher> page = new Page<>(current,limit);
        eduTeacherService.page(page,wrapper);
        List<EduTeacher> records = page.getRecords();
        long total = page.getTotal();
        //1、存入map
        Map<String,Object> map = new HashMap<>();
        map.put("list",records);
        map.put("total",total);
        return Result.succ().data(map);
        //2、直接拼接
//        return Result.succ().data("list",records).data("total",total);
    }

    @ApiOperation(value = "添加讲师")
    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return Result.succ();
        }else{
            return Result.error();
        }
    }

    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("getAllById/{id}")
    public Result getAllById(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return Result.succ().data("eduTeacher",eduTeacher);
    }

    @ApiOperation(value = "修改讲师")
    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean update = eduTeacherService.updateById(eduTeacher);
        if (update){
            return Result.succ();
        } else {
            return Result.error();
        }
    }

}

