package com.zgz.servicestatistics.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zgz.servicestatistics.pojo.StatisticsDaily;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author willie
 * @since 2021-12-07
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    void createStaDaily(String day);

    Map<String, Object> getStaDaily(String type, String begin, String end);
}
