package cc.akali.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author: dan
 * @Date: 2020/5/19 19:30
 * @Description:
 */
@Data
public class DemoData {
    //设置excel表头的名称
    //设置excel表头名称
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;
    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;
}
