package cc.akali.edu_service.service;

import cc.akali.edu_service.entity.EduSubject;
import cc.akali.edu_service.entity.subject.OneSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author dan
 * @since 2020-05-20
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile fil,EduSubjectService eduSubjectService);

    List<OneSubject> getAllSubject();
}
