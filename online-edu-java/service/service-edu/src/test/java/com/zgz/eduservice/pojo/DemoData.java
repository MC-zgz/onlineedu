package com.zgz.eduservice.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author willie
 * @date 2021/11/10
 */
@Data
public class DemoData {
    //设置表头名称，读取的时候要加value 和索引index
    @ExcelProperty(value = "学生编号",index = 0)
    private int sno;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String name;
}
