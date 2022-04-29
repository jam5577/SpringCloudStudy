package com.jam.adapter.enums;

/**
 * @program: SpringbootStudy
 * @description: 错误类型
 * @author: Mr.Pu
 * @create: 2022-01-07 15:25
 **/

public enum ResultInfo {

    /**
     * 成功操作返回结果，200+++
     */
    SUCCESS(200, "操作成功!"),
    LOGIN_SUCCESS(201, "登录成功!"),
    TOKEN_GENERATE_SUCCESS(202, "token生成成功"),
    /**
     * 错误操作返回结果，300+++
     */
    LOGIN_FAILURE(300, "登录失败!"),
    ERROR(301, "操作失败!"),
    SMS_EXPIRED(302, "短信验证码过期"),
    SMS_NOT_MATCHING(303, "短信验证码不匹配"),
    USERNAME_PASSWORD_NOT_MATCHING(304, "用户名或密码不正确"),
    ANONYMOUS_ENTRY(305, "匿名用户/未登录用户访问"),
    TOKEN_EXPIRED(306, "TOKEN信息过期，请重新登陆"),
    TOKEN_NOT_MATCHING(307, "TOKEN信息不匹配"),

    /**
     * 被拦截操作返回结果，400+++
     */
    BAD_REQUEST(400, "请求错误"),

    UNAUTHORIZED(401, "未登录"),

    FORBIDDEN(403, "无权访问"),

    NOT_FOUND(404, "未找到"),

    USER_NAME_DUPLICATE(405, "用户名重复"),
    USER_BANNED(406, "用户被封禁"),

    /**
     * 系统错误类型，500+++
     */
    INNER_ERROR(500, "系统内部错误"),

    GATEWAY_ERROR(502, "系统网关拦截");


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
