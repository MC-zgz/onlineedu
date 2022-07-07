package com.zgz.servicestatistics.controller;


import com.zgz.commonutils.Result;
import com.zgz.servicestatistics.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author willie
 * @since 2021-12-07
 */
@Api(description="统计分析管理")
@RestController
@RequestMapping("/servicestatistics/statistics-daily")
@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @ApiOperation("生成统计数据")
    @PostMapping("/createStaDaily/{day}")
    public Result createStaDaily(@PathVariable String day){
        statisticsDailyService.createStaDaily(day);
        return Result.succ();
    }

    @ApiOperation("查询统计数据")
    @GetMapping("/getStaDaily/{type}/{begin}/{end}")
    public Result getStaDaily(@PathVariable String type,@PathVariable String begin,@PathVariable String end){
        Map<String,Object> map = statisticsDailyService.getStaDaily(type,begin,end);
        return Result.succ().data(map);
    }



}

