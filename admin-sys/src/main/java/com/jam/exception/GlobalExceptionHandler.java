package com.jam.exception;

import com.jam.enums.ExceptionType;
import com.jam.handler.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: PureEcho
 * @description: 全局异常处理类
 * @author: Mr.Pu
 * @create: 2022-02-01 21:59
 **/
@Order(0)
@Slf4j
@RestControllerAdvice  //用于给controller增加一些附加操作
public class GlobalExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(value = GlobalException.class)
    @ResponseBody
    public Result<Object> exception(GlobalException e,HttpServletRequest request) {
        log.info("异常信息为:{},异常URI为:{}", e.getMessage(),request.getRequestURI());
        return Result.error(ExceptionType.BAD_REQUEST,request.getRequestURI());
    }

    @ExceptionHandler(value = Throwable.class)
    public Result<Object> exception(ExceptionType exceptionEnum) {
        log.info("异常信息为{}", exceptionEnum.getMessage());
        return Result.error(exceptionEnum);
    }


}
