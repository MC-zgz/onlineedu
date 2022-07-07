package com.zgz.servicestatistics.Scheduled;

import com.zgz.servicestatistics.service.StatisticsDailyService;
import com.zgz.servicestatistics.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author willie
 * @date 2021/12/8
 */
@Component
public class ScheduledTask {
    @Autowired
    private StatisticsDailyService statisticsDailyService;


    //每天凌晨1点跑前一天数据
    @Scheduled(cron = "0 0 1 * * ? ")
    public void task2(){
        String day = DateUtil.formatDate(DateUtil.addDays(new Date(),-1));
        statisticsDailyService.createStaDaily(day);
        System.out.println("生成数据成功"+day);
    }

}
