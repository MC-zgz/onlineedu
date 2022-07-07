package com.zgz.eduservice.test;

import com.alibaba.excel.EasyExcel;
import com.zgz.eduservice.listerner.ExcelListerner;
import com.zgz.eduservice.pojo.DemoData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author willie
 * @date 2021/11/10
 */
public class Test {
    //写入
    @org.junit.Test
    public void writeExcel(){
        String fileName = "D:\\zgz_work\\TestExcel\\write.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(data());

    }

    //读取
    @org.junit.Test
    public void readExcel(){
        String fileName = "D:\\zgz_work\\TestExcel\\write.xlsx";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        // 如果这里想使用03 则 传入excelType参数即可
        EasyExcel.read(fileName,DemoData.class,new ExcelListerner()).sheet().doRead();

    }

    private static List<DemoData> data() {
        List<DemoData> list = new ArrayList<DemoData>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setName("张三"+i);
            list.add(data);
        }
        return list;
    }

}
