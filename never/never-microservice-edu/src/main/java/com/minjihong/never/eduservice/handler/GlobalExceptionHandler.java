package com.minjihong.never.eduservice.handler;

import com.minjihong.never.common.vo.R;
import com.minjihong.never.eduservice.exception.EduException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    //统一异常处理
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        e.printStackTrace();
        return R.error().message("出现异常");
    }

    //自定义异常处理
    @ExceptionHandler(EduException.class)
    @ResponseBody
    public R error(EduException e) {
        e.printStackTrace();
        return R.error().message(e.getMessage()).code(e.getCode());
    }

}
