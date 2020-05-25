package cc.akali.oss.controller;

import cc.akali.commonutils.Result;
import cc.akali.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: dan
 * @Date: 2020/5/14 15:43
 * @Description:
 */
@RestController
@RequestMapping("/edu_oss/fileOss")
@CrossOrigin
@Api(tags = {"阿里oss测试"})
public class OssController {
    @Autowired
    private OssService ossService;

    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public Result uploadOssFile(MultipartFile file) throws IOException {
        //获取上传文件
        //返回上传路径
       String url= ossService.uploadFileAvatar(file);
        return Result.ok().data("url",url);
    }
}
