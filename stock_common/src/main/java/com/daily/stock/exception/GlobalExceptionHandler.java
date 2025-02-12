package com.daily.stock.exception;

import com.daily.stock.dtos.R;
import com.daily.stock.dtos.ResponseCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  全局异常处理
 */
@RestControllerAdvice  // 底层是AOP切面
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DailyIndexException.class)
    public R DailyIndexExceptionHandler(DailyIndexException e){
        return R.error(e.getStatus(),e.getMessage());
    }

    /**
     * 系统异常（个人建议开发的时候别写 项目上线了再写）
     */
//    @ExceptionHandler(Exception.class)
//    public ResponseResult exceptionHandler(Exception e){
//        return ResponseResult.errorResult(500,"服务器繁忙！");
//    }

}
