package com.jam.base.exception;

import org.springframework.http.HttpStatus;

/**
 * @program: SpringCloudStudy
 * @description: 用户错误
 * @author: Mr.Pu
 * @create: 2022-03-08 23:17
 **/

public class UserException extends GlobalException {
    public UserException(Integer code, String message) {
        super(code, message);
    }

    public UserException(ExceptionType exceptionType) {
        super(exceptionType);
    }

    public UserException(HttpStatus httpStatus) {
        super(httpStatus);
    }
}
