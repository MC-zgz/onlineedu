package com.zgz.servicebase.handler;

import com.zgz.commonutils.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author willie
 * @date 2021/10/5
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error();
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException e){
        e.printStackTrace();
        return Result.error().message("特殊异常处理");
    }

    @ExceptionHandler(OnlineEduException.class)
    @ResponseBody
    public Result error(OnlineEduException e){
        e.printStackTrace();
        return Result.error().message(e.getMsg()).code(e.getCode());
    }
}
