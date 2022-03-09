package com.jam.base.exception;

public enum ExceptionType {
    ANONYMOUS_ENTRY(305, "匿名用户/未登录用户访问"),
    USER_KICKED_OUT(306, "用户被踢出"),
    CODE_VALIDATE_ERROR(307, "验证码错误 "),
    BAD_REQUEST(400, "错误请求异常"),
    UNAUTHORIZED(401, "未认证异常"),
    PAYMENT_REQUIRED(402, "Payment Required"),
    FORBIDDEN(403, "禁止访问"),
    NOT_FOUND(404, "页面未找到"),
    METHOD_NOT_ALLOWED(405, "方法请求错误"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    PROXY_AUTHENTICATION_REQUIRED(407, "代理需要认证"),
    REQUEST_TIMEOUT(408, "请求超时"),
    CONFLICT(409, "请求冲突"),
    LOGIN_FAILURE(410, "登录失败"),
    USER_NOT_FOUND(411, "用户不存在"),
    USERNAME_PASSWORD_NOT_MATCHING(412, "用户名或密码不正确"),
    DUPLICATE_USERNAME(413, "用户名重复"),
    INTERNAL_SERVER_ERROR(500, "内部代码错误"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    BAD_GATEWAY(502, "网关错误"),
    SERVICE_UNAVAILABLE(503, "不可用的服务"),
    GATEWAY_TIMEOUT(504, "网关超时"),
    HTTP_VERSION_NOT_SUPPORTED(505, "不支持的HTTP请求");


    private final Integer code;
    private final String message;

    ExceptionType(Integer code, String message) {
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
