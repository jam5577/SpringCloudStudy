package com.jam.exception;

import com.jam.enums.ExceptionType;

/**
 * @program: SpringCloudStudy
 * @description: 登录异常处理类
 * @author: Mr.Pu
 * @create: 2022-02-13 14:33
 **/

public class LoginException extends GlobalException {
    public LoginException(Integer code, String message) {
        super(code, message);
    }

    public LoginException(ExceptionType exceptionType) {
        super(exceptionType);
    }
}
