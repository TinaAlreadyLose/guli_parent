package cc.akali.edu_service.controller;


import cc.akali.commonutils.Result;
import cc.akali.edu_service.entity.EduTeacher;
import cc.akali.edu_service.entity.vo.TeacherQuery;
import cc.akali.edu_service.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author dan
 * @since 2020-04-29
 */
@Api(value = "讲师管理", tags = {"讲师管理"})
@RestController
@RequestMapping("/edu_service/teacher")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    //restful 风格
    //查询讲师表的所有数据
    @ApiOperation(value = "查询所有讲师列表")
    @GetMapping("findAll")
    public Result findAllTeacher() {
//        try {
//            int i = 10 / 0;
//        } catch (Exception e) {
//            throw new GuliException(20001, "执行了自定义异常处理");
//        }
        List<EduTeacher> list = eduTeacherService.list(null);
        return Result.ok().data("items", list);
    }

    @ApiOperation(value = "逻辑删除讲师方法")
    @DeleteMapping("remove/{id}")
    public Result removeTeacher(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable("id") String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        if (eduTeacher == null) {
            return Result.error();
        } else {
            eduTeacherService.removeById(id);
            return Result.ok();
        }
    }
    @ApiOperation(value = "批量逻辑删除讲师方法")
    @DeleteMapping("removeByIds")
    public Result removeTeacherByIDs(@ApiParam(name = "ids", value = "讲师ids", required = true) @RequestBody List<String> ids) {
//        System.out.println(ids);
        if(eduTeacherService.removeByIds(ids))
            return Result.ok();
        else  return Result.error();
    }


    @ApiOperation(value = "分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public Result pageListTeacher(@ApiParam(name = "current", value = "当前页数") @PathVariable("current") Long current, @ApiParam(name = "limit", value = "每页显示条数") @PathVariable("limit") Long limit) {

        Page<EduTeacher> eduTeacherPage = new Page<>(current, limit);
        eduTeacherService.page(eduTeacherPage, null);
        long total = eduTeacherPage.getTotal();
        List<EduTeacher> records = eduTeacherPage.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("records", records);
        return Result.ok().data(map);
    }

    //条件查询带分页,@RequestBody 用json传数据 ,必须是post请求,required=false可以没有
    @ApiOperation(value = "带分页的条件查询-讲师")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public Result pageTeacherCondition(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) TeacherQuery teacherQuery) {
        Page<EduTeacher> pageTeacher = new Page<>(current, limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //根据条件值拼接,注意此为数据库中的字段名
        if (!StringUtils.isEmpty(teacherQuery.getName())) {
            wrapper.like("name", teacherQuery.getName());
        }
        if (!StringUtils.isEmpty(teacherQuery.getLevel())) {
            wrapper.eq("level", teacherQuery.getLevel());
        }
        if (!StringUtils.isEmpty(teacherQuery.getBegin())) {
            wrapper.ge("gmt_create", teacherQuery.getBegin());
        }
        if (!StringUtils.isEmpty(teacherQuery.getEnd())) {
            wrapper.le("gmt_create", teacherQuery.getEnd());

        }
        wrapper.orderByDesc("gmt_create");
        eduTeacherService.page(pageTeacher, wrapper);

        Long total = pageTeacher.getTotal();//获取总的记录数
        List<EduTeacher> records = pageTeacher.getRecords();//获取数据list集合
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", total);
        map.put("records", records);
        return Result.ok().data(map);
    }

    //添加讲师接口方法
    @ApiOperation(value = "添加-讲师")
    @PostMapping("addTeacher")
    public Result addTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean save = eduTeacherService.save(eduTeacher);
        if (save) {
            return Result.ok();
        } else return Result.error();
    }

    //根据讲师id进行查询
    @ApiOperation(value = "根据id查询-讲师")
    @GetMapping("getTeacher/{id}")
    public Result getTeacher(@PathVariable("id") String id) {
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        if (eduTeacher != null)
            return Result.ok().data("teacher", eduTeacher);
        else return Result.error();
    }

//    //@RequestBody 必须使用post,不用需重设id值
//    @ApiOperation(value = "根据id修改-讲师")
//    @PutMapping("updateTeacher/{id}")
//    public Result updateTeacher(@ApiParam(name = "id", value = "讲师id", required = true) @PathVariable("id") String id,
//                                @ApiParam(name = "eduTeacher", value = "讲师对象", required = true)
//                                @RequestBody EduTeacher eduTeacher) {
//        eduTeacher.setId(id);//手动设置id
//        boolean flag = eduTeacherService.updateById(eduTeacher);
//        if (flag)
//            return Result.ok();
//        else return Result.error();
//    }

    @PostMapping("updateTeacher")
    public Result updateTeacher(@RequestBody EduTeacher eduTeacher) {
        boolean flag=eduTeacherService.updateById(eduTeacher);
        if (flag) {
            return Result.ok();
        } else return Result.error();
    }
}

