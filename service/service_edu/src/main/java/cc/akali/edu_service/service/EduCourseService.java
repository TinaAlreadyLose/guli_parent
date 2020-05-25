package cc.akali.edu_service.service;

import cc.akali.edu_service.entity.EduCourse;
import cc.akali.edu_service.entity.vo.CourseInfoVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author dan
 * @since 2020-05-24
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);
}
