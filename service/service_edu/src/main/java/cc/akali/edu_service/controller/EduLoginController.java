package cc.akali.edu_service.controller;

import cc.akali.commonutils.Result;
import com.baomidou.mybatisplus.extension.api.R;
import com.sun.org.apache.regexp.internal.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 1,2,3,4,5,6,7,8,9,10,11
 * @Author: dan
 * @Date: 2020/5/7 20:25
 * @Description:
 */
@RestController
@RequestMapping("/edu_service/user")
@Api(value = "讲师后台登入", tags = {"讲师后台登入"})
@CrossOrigin
public class EduLoginController {
    @ApiOperation(value = "登入")
    @PostMapping("login")
    public Result login() {
        return Result.ok().data("token","admin");
    }
    @ApiOperation(value = "获取登入info")
    @GetMapping("info")
    public Result info() {
        return Result.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
