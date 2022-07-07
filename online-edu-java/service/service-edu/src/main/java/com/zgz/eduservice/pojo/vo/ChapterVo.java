package com.zgz.eduservice.pojo.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author willie
 * @date 2021/11/14
 */
@Data
public class ChapterVo {
    private String id;

    private String title;

    private List<VideoVo> children =new ArrayList<>();

}
