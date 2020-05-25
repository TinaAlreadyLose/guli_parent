package cc.akali.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @Author: dan
 * @Date: 2020/5/19 20:28
 * @Description:
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {
    //一行一行读取数据
    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        System.out.println("******" + demoData);
    }

    //读取表头的内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头: "+headMap);
    }

    //读取完所有数据后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
