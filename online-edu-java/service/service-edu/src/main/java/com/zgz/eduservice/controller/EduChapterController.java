package com.zgz.eduservice.controller;


import com.zgz.commonutils.Result;
import com.zgz.eduservice.pojo.EduChapter;
import com.zgz.eduservice.pojo.vo.ChapterVo;
import com.zgz.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author willie
 * @since 2021-11-14
 */
@Api(description = "章节管理")
@RestController
@RequestMapping("/eduservice/edu-chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation(value = "根据课程id查询章节、小节信息")
    @GetMapping("/getChapterVideoById/{courseId}")
    public Result getChapterVideoById(@PathVariable String courseId){
        List<ChapterVo> chapterVoList = eduChapterService.getChapterVideoById(courseId);
        return Result.succ().data("chapterVideoList",chapterVoList);
    }

    @ApiOperation(value = "添加章节")
    @PostMapping("/addChapter")
    public Result addChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);
        return Result.succ().data("eduChapter",eduChapter);
    }

    @ApiOperation(value = "根据Id删除章节")
    @DeleteMapping("/delChapter/{id}")
    public Result delChapter(@PathVariable String id){
        eduChapterService.removeById(id);
        return Result.succ();
    }

    @ApiOperation(value = "根据Id查询章节")
    @GetMapping("/getChapterById/{id}")
    public Result getChapterById(@PathVariable String id){
        EduChapter eduChapter = eduChapterService.getById(id);
        return Result.succ().data("eduChapter",eduChapter);
    }

    @ApiOperation(value = "根据Id修改章节")
    @PostMapping("/updChapter")
    public Result updChapter(@RequestBody EduChapter eduChapter){
        eduChapterService.updateById(eduChapter);
        return Result.succ().data("eduChapter",eduChapter);
    }
}

