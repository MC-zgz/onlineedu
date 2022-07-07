package com.zgz.eduservice.listerner;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zgz.eduservice.pojo.DemoData;

/**
 * @author willie
 * @date 2021/11/10
 */
public class ExcelListerner extends AnalysisEventListener<DemoData> {
    //读取每一行数据
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println(demoData);
    }

    //数据读取完做的事
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
