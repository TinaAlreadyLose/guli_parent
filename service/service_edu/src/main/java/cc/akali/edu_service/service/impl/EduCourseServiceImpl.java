package cc.akali.edu_service.service.impl;

import cc.akali.edu_service.entity.EduCourse;
import cc.akali.edu_service.entity.EduCourseDescription;
import cc.akali.edu_service.entity.vo.CourseInfoVo;
import cc.akali.edu_service.mapper.EduCourseMapper;
import cc.akali.edu_service.service.EduCourseDescriptionService;
import cc.akali.edu_service.service.EduCourseService;
import cc.akali.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author dan
 * @since 2020-05-24
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    EduCourseDescriptionService eduCourseDescriptionService;
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setStatus(EduCourse.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        boolean resultCourse = this.save(eduCourse);
        if(!resultCourse) {
            throw  new GuliException(20001,"添加课程失败");
        }
        String cid = eduCourse.getId();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(cid);
        eduCourseDescription.setDescription((courseInfoVo.getDescription()));
        boolean resultDescription=eduCourseDescriptionService.save(eduCourseDescription);
        if (!resultDescription) {
            throw  new GuliException(20001,"课程详情信息保存失败");
        }
        return cid;
    }
}
