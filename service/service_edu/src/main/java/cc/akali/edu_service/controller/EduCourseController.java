package cc.akali.edu_service.controller;


import cc.akali.commonutils.Result;
import cc.akali.edu_service.entity.vo.CourseInfoVo;
import cc.akali.edu_service.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author dan
 * @since 2020-05-24
 */
@RestController
@RequestMapping("/edu_service/edu-course")
@Api(value = "课程基本信息表", tags = {"课程基本信息表"})
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping("addCourseInfo")
    @ApiOperation(value = "添加课程的基本信息")
    public Result addCourseInfo(  @ApiParam(name = "CourseInfoForm", value = "课程基本信息", required = true)@RequestBody CourseInfoVo courseInfoVo) {
       String id= eduCourseService.saveCourseInfo(courseInfoVo);

        return Result.ok().data("courseId",id);
    }
}

