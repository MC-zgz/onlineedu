package com.zgz.eduservice.service;

import com.zgz.eduservice.pojo.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zgz.eduservice.pojo.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author willie
 * @since 2021-11-14
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVideoById(String courseId);
}
