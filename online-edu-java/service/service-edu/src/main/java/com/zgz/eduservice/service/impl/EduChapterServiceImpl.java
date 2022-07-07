package com.zgz.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgz.eduservice.pojo.EduChapter;
import com.zgz.eduservice.mapper.EduChapterMapper;
import com.zgz.eduservice.pojo.EduVideo;
import com.zgz.eduservice.pojo.vo.ChapterVo;
import com.zgz.eduservice.pojo.vo.VideoVo;
import com.zgz.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgz.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author willie
 * @since 2021-11-14
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {
    @Autowired
    private EduVideoService eduVideoService;

    //根据课程id查询章节、小节信息
    @Override
    public List<ChapterVo> getChapterVideoById(String courseId) {
        //创建条件构造器
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        //1.根据课程id查询章节信息
        chapterQueryWrapper.eq("course_id",courseId);
        List<EduChapter> chapterList = baseMapper.selectList(chapterQueryWrapper);
        //2.根绝课程id查询小节信息
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id",courseId);
        List<EduVideo> videoList = eduVideoService.list(eduVideoQueryWrapper);
        //3.遍历章节，进行封装
        List<ChapterVo> chapterVideoList = new ArrayList<>();
        for (int i = 0; i < chapterList.size(); i++) {
            EduChapter eduChapter = chapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            chapterVideoList.add(chapterVo);
            //4.遍历此章节关联小节信息进行封装
            List<VideoVo> videoVos = new ArrayList<>();
            for (int j = 0; j < videoList.size(); j++) {
                EduVideo eduVideo = videoList.get(j);
                if(eduChapter.getId().equals(eduVideo.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoVos.add(videoVo);
                }
                chapterVo.setChildren(videoVos);
            }
        }
        return chapterVideoList;
    }
}
