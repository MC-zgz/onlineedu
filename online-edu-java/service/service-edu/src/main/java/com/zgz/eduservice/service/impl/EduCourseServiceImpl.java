package com.zgz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zgz.eduservice.client.VideoClient;
import com.zgz.eduservice.pojo.EduChapter;
import com.zgz.eduservice.pojo.EduCourse;
import com.zgz.eduservice.mapper.EduCourseMapper;
import com.zgz.eduservice.pojo.EduCourseDescription;
import com.zgz.eduservice.pojo.EduVideo;
import com.zgz.eduservice.pojo.vo.CourseInfoForm;
import com.zgz.eduservice.pojo.vo.CoursePublishVo;
import com.zgz.eduservice.pojo.vo.CourseQueryVo;
import com.zgz.eduservice.pojo.vo.CourseWebVo;
import com.zgz.eduservice.service.EduChapterService;
import com.zgz.eduservice.service.EduCourseDescriptionService;
import com.zgz.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgz.eduservice.service.EduVideoService;
import com.zgz.servicebase.handler.OnlineEduException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author willie
 * @since 2021-11-13
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VideoClient videoClient;

    //添加课程
    @Override
    public String addCourseInfo(CourseInfoForm courseInfoForm) {
        //1.添加课程信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if(insert == 0){
            throw new OnlineEduException(20001,"创建课程失败！");
        }
        //2.获取课程id
        String courseId = eduCourse.getId();

        //3.添加课程描述信息
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setId(courseId);
        courseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescriptionService.save(courseDescription);
        return courseId;
    }
    //根据id查询课程信息
    @Override
    public CourseInfoForm getCourseInfoById(String id) {
        //1根据id查询课程信息
        EduCourse eduCourse = baseMapper.selectById(id);
        //2封装课程信息
        CourseInfoForm courseInfoForm = new CourseInfoForm();
        BeanUtils.copyProperties(eduCourse,courseInfoForm);
        //3根据id查询课程描述信息
        EduCourseDescription courseDescription = eduCourseDescriptionService.getById(id);
        //4封装课程描述
        courseInfoForm.setDescription(courseDescription.getDescription());
        return courseInfoForm;
    }

    //修改课程信息
    @Override
    public void updateCourseInfo(CourseInfoForm courseInfoForm) {
        //1.复制课程信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoForm,eduCourse);
        //2.更新课程数据
        int update = baseMapper.updateById(eduCourse);
        //3.判断是否更新成功
        if(update == 0){
            throw new OnlineEduException(20001,"更新失败！");
        }
        //4.更新课程描述
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoForm.getId());
        eduCourseDescription.setDescription(courseInfoForm.getDescription());
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    //根据课程id查询课程发布信息
    @Override
    public CoursePublishVo getCoursePublishById(String id) {
        CoursePublishVo coursePublishVo = baseMapper.getCoursePublishById(id);

        return coursePublishVo;
    }

    //根据id删除课程相关信息
    @Override
    public void delCourseInfo(String id) {
        // 1.删除视频
        //1.1 查询小结信息
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id",id);
        List<EduVideo> list = eduVideoService.list(videoQueryWrapper);
        //1.2 遍历获取视频id
        List<String> videoIdList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            EduVideo eduVideo = list.get(i);
            videoIdList.add(eduVideo.getVideoSourceId());
        }
        //1.3判断 调用接口
        if(videoIdList.size() > 0){
            videoClient.delVideoList(videoIdList);
        }

        //2.删除小节
        QueryWrapper<EduVideo> videoWrapper = new QueryWrapper<>();
        videoWrapper.eq("course_id",id);
        eduVideoService.remove(videoWrapper);
        System.out.println("删除小节成功");
        //3.删除章节
        QueryWrapper<EduChapter> chapterWrapper = new QueryWrapper<>();
        chapterWrapper.eq("course_id",id);
        eduChapterService.remove(chapterWrapper);
        System.out.println("删除章节成功");
        //4.删除课程描述
        eduCourseDescriptionService.removeById(id);
        System.out.println("删除课程描述成功");
        //5.删除课程
        int delete = baseMapper.deleteById(id);
        if(delete ==0){
            throw new OnlineEduException(20001,"删除课程失败！");
        }
        System.out.println("删除课程成功");
    }

    //前台带条件分页查询课程列表
    @Override
    public Map<String, Object> getCourseApiPageVo(Page<EduCourse> pageParam, CourseQueryVo courseQueryVo) {
        //1 取出查询条件
        String subjectParentId = courseQueryVo.getSubjectParentId();
        String subjectId = courseQueryVo.getSubjectId();
        String buyCountSort = courseQueryVo.getBuyCountSort();
        String gmtCreateSort = courseQueryVo.getGmtCreateSort();
        String priceSort = courseQueryVo.getPriceSort();
        //2 验空，不为空拼写到查询条件
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();

        if(!StringUtils.isEmpty(subjectParentId)){
            queryWrapper.eq("subject_parent_id",subjectParentId);
        }
        if(!StringUtils.isEmpty(subjectId)){
            queryWrapper.eq("subject_id",subjectId);
        }
        if(!StringUtils.isEmpty(buyCountSort)){
            queryWrapper.orderByDesc("buy_count");
        }
        if(!StringUtils.isEmpty(gmtCreateSort)){
            queryWrapper.orderByDesc("gmt_create");
        }
        if(!StringUtils.isEmpty(priceSort)){
            queryWrapper.orderByDesc("price");
        }
        queryWrapper.eq("status","Normal");

        //3 分页查询
        baseMapper.selectPage(pageParam,queryWrapper);
        //4 封装数据
        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        return map;

    }

    //前台根据课程id查询课程相关信息
    @Override
    public CourseWebVo getCourseWebInfo(String courseId) {
        CourseWebVo courseWebVo = baseMapper.getCourseWebInfo(courseId);
        return courseWebVo;
    }
}
