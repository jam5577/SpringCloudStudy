package com.jam.base.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * @program: SpringCloudStudy
 * @description: 统一异常处理类
 * @author: Mr.Pu
 * @create: 2022-02-12 21:44
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class GlobalException extends RuntimeException {

    private final Integer code;
    private final String message;

    public GlobalException(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public GlobalException(ExceptionType exceptionType) {
        this.code = exceptionType.getCode();
        this.message = exceptionType.getMessage();
    }


    public GlobalException(HttpStatus httpStatus) {
        this.code = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }

}
