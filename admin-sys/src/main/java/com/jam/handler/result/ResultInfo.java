package com.jam.handler.result;

/**
 * @program: SpringbootStudy
 * @description: 错误类型
 * @author: Mr.Pu
 * @create: 2022-01-07 15:25
 **/

public enum ResultInfo {

    SUCCESS(200,"操作成功!"),
    LOGIN_SUCCESS(200,"登录成功!"),
    LOGIN_FAILURE(300,"登录失败!"),
    ANONYMOUS_ENTRY(305,"匿名用户/未登录用户访问"),
    INNER_ERROR(500,"系统内部错误"),
    UNAUTHORIZED(401,"未登录"),
    BAD_REQUEST(400,"请求错误"),
    FORBIDDEN(403,"无权访问"),
    NOT_FOUND(404,"未找到"),
    USER_NAME_DUPLICATE(40001,"用户名重复"),
    ERROR(301,"操作失败!");


    private final Integer code;
    private final String message;

    ResultInfo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
