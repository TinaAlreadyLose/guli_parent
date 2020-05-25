package cc.akali.edu_service.service.impl;

import cc.akali.edu_service.entity.EduSubject;
import cc.akali.edu_service.entity.excel.SubjectData;
import cc.akali.edu_service.entity.subject.OneSubject;
import cc.akali.edu_service.entity.subject.TwoSubject;
import cc.akali.edu_service.listener.SubjectExcelListener;
import cc.akali.edu_service.mapper.EduSubjectMapper;
import cc.akali.edu_service.service.EduSubjectService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author dan
 * @since 2020-05-20
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file,EduSubjectService eduSubjectService) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(eduSubjectService)).sheet().doRead();
        } catch (Exception e) {

        }

    }

    @Override
    public List<OneSubject> getAllSubject() {
        //查所有的一级标题
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", 0);
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);
        //查所有的二级标题
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", 0);
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);
        //用于封装最后的数据
        List<OneSubject> finalSubjectList = new ArrayList<>();
        for(EduSubject subject : oneSubjectList){
            OneSubject oneSubject = new OneSubject();
//            oneSubject.setId(subject.getId());
//            oneSubject.setTitle(subject.getTitle());
            BeanUtils.copyProperties(subject, oneSubject);
            finalSubjectList.add(oneSubject);
            List<TwoSubject> twoFinalSubject = new ArrayList<>();
            for (EduSubject subject2 : twoSubjectList) {
                if (subject2.getParentId().equals(subject.getId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(subject2,twoSubject);
//                    System.out.println(twoSubject);
                    twoFinalSubject.add(twoSubject);
                }
            }
            oneSubject.setChildren(twoFinalSubject);
        }
        return finalSubjectList;
    }
}
