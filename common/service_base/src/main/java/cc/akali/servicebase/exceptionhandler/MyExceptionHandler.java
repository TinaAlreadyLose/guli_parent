package cc.akali.servicebase.exceptionhandler;

import cc.akali.commonutils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常处理
 * @Author: dan
 * @Date: 2020/4/29 15:55
 * @Description:
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody//返回数据
    //全局异常
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error().message("执行了全局异常处理");
    }
    //特定异常异常
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody//返回数据
    public Result error(ArithmeticException e) {
        e.printStackTrace();
        return Result.error().message("执行了ArithmeticException异常处理");
    }
    //自定义异常处理
    @ExceptionHandler(GuliException.class)
    @ResponseBody//返回数据
    public Result error(GuliException e) {
        log.error(e.getMessage());
        log.info("");
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());

    }
}
