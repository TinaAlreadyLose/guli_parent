package cc.akali.edu_service.controller;


import cc.akali.commonutils.Result;
import cc.akali.edu_service.entity.subject.OneSubject;
import cc.akali.edu_service.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author dan
 * @since 2020-05-20
 */
@RestController
@CrossOrigin
@RequestMapping("/edu_service/subject")
@Api(value = "课程分类管理", tags = {"课程分类管理"})
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    //添加课程分类,获取上传文件,把内容读出来
    @PostMapping("addSubject")
    @ApiOperation(value = "通过excel添加课程")
    public Result addSubject(MultipartFile file) {
        eduSubjectService.saveSubject(file, eduSubjectService);
        return Result.ok();
    }

    @GetMapping("getALlSubject")
    @ApiOperation(value = "课程分类列表树形图")
    public Result getAllSubject() {
        List<OneSubject> list=eduSubjectService.getAllSubject();
        return Result.ok().data("list",list);
    }
}

