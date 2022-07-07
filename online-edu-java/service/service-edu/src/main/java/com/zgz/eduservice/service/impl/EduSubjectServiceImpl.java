package com.zgz.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zgz.eduservice.listener.SubjectExcelListener;
import com.zgz.eduservice.pojo.EduSubject;
import com.zgz.eduservice.mapper.EduSubjectMapper;
import com.zgz.eduservice.pojo.vo.ExcelSubjectData;
import com.zgz.eduservice.pojo.vo.OneSubjectVo;
import com.zgz.eduservice.pojo.vo.TwoSubjectVo;
import com.zgz.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zgz.servicebase.handler.OnlineEduException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author willie
 * @since 2021-11-10
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    //导入课程分类
    @Override
    public void addSubject(MultipartFile file, EduSubjectService eduSubjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, ExcelSubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
            throw new OnlineEduException(20001,"导入课程分类失败！");
        }
    }


    //查询所有课程分类
    @Override
    public List<OneSubjectVo> getAllSubject() {

        //1.查询所有一级分类
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id","0");
        List<EduSubject> eduSubjects1 = baseMapper.selectList(wrapperOne);

        //2.查询所有二级分类
        QueryWrapper<EduSubject> wrapperTweo = new QueryWrapper<>();
        wrapperTweo.ne("parent_id","0");
        List<EduSubject> eduSubjects2 = baseMapper.selectList(wrapperTweo);

        //3.封装一级分类
        List<OneSubjectVo> allSubject = new ArrayList<>();
        for (int i = 0; i < eduSubjects1.size() ; i++) {
            //3.1取出每一个一级分类
            EduSubject oneSubject = eduSubjects1.get(i);
            //3.2 EduSubject转换成oneSubjectVo
            OneSubjectVo oneSubjectVo = new OneSubjectVo();
//            oneSubjectVo.setId(oneSubject.getId());
//            oneSubjectVo.setTitle(oneSubject.getTitle());
            BeanUtils.copyProperties(oneSubject,oneSubjectVo);
            allSubject.add(oneSubjectVo);
            //4.找到跟一级相关联的二级封装
            List<TwoSubjectVo> twoSubjectVos = new ArrayList<>();
            for (int m = 0; m < eduSubjects2.size(); m++) {
                //4.1取出每个二级分类
                EduSubject towSubject = eduSubjects2.get(m);
                //4.2判断是否归属于子一级、
                if(towSubject.getParentId().equals(oneSubject.getId())){
                    //4.3 EduSubject转换成towSubjectVo
                    TwoSubjectVo twoSubjectVo = new TwoSubjectVo();
                    BeanUtils.copyProperties(towSubject,twoSubjectVo);
                    twoSubjectVos.add(twoSubjectVo);
                }
            }
            oneSubjectVo.setChildren(twoSubjectVos);
        }
        return allSubject;
    }
}
