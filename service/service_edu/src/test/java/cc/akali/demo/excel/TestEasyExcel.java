package cc.akali.demo.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dan
 * @Date: 2020/5/19 19:34
 * @Description:
 */
public class TestEasyExcel {
    public static void main(String[] args) {
        //实现excel写操作
        //设置写入文件的地址与excel名称
        String fileName = "E:/Java/Data/write.xlsx";

        //调用easyExcel
        //write 方法两个参数,第一个参数文件路径名称,,第二个参数实体类class
//        EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(getData());
        EasyExcel.write(fileName, DemoData.class).sheet("学生表").doWrite(getData());
//        EasyExcel.write(fileName,DemoData.class).sheet("学生列表").doWrite(getData());
//        System.out.println(getData());
    }
    @Test
    public  void writeExcelDate(){
        String fileName = "E:/Java/Data/write.xlsx";
        EasyExcel.read(fileName, DemoData.class, new ExcelListener()).sheet().doRead();
    }

    //创建方法范围list
    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy" + i);
            list.add(data);
        }
        return list;
    }
}
