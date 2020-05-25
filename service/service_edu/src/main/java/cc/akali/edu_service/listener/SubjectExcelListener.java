package cc.akali.edu_service.listener;

import cc.akali.edu_service.entity.EduSubject;
import cc.akali.edu_service.entity.excel.SubjectData;
import cc.akali.edu_service.service.EduSubjectService;
import cc.akali.servicebase.exceptionhandler.GuliException;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @Author: dan
 * @Date: 2020/5/20 15:07
 * @Description:
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    //SubjectExcelListener不能交给spring管理,,要自己new,不能注入其他对象,,不能用spring的数据库操纵
    public EduSubjectService eduSubjectService;

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }
    public SubjectExcelListener( ) {}

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData==null)
            throw new GuliException(20001, "文件数据为空");
        //一行一行读取,每次读取有两个值,第一个胃一级分类,第二个为二级分类
        EduSubject existOneSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if (existOneSubject==null) {//没有相同的一级分类
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());//设置一级分类名称
            eduSubjectService.save(existOneSubject);
        }
        //获取一级分类的id值
        String parentId = existOneSubject.getId();
        EduSubject existTwoSubject= this.existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), parentId);
        if (existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(parentId);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());//二级分类名称
            eduSubjectService.save(existTwoSubject);
        }
    }

    //判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService eduSubjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        return eduSubjectService.getOne(wrapper);
    }
    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService, String name,String parentId) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", parentId);
        return eduSubjectService.getOne(wrapper);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
