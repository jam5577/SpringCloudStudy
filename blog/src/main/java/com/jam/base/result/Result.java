package com.jam.base.result;

import com.jam.base.exception.ExceptionType;
import lombok.Data;

import javax.annotation.Nullable;

/**
 * @program: admin-sys
 * @description: 公共返回类
 * @author: Mr.Pu
 * @create: 2021-12-31 21:14
 **/
@Data
public class Result<T> {
    /**
     * 返回 状态成功 或者失败
     */
    private boolean status;
    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public static <T> Result<T> success() {
        return resultData(true, ResultInfo.SUCCESS, null);
    }

    public static <T> Result<T> success(String message) {
        return resultData(true, 200, message, null);
    }

    public static <T> Result<T> success(T data) {
        return resultData(true, 200, null, data);
    }

    public static <T> Result<T> success(@Nullable String message,
                                        @Nullable T data) {
        return resultData(true, 200, message, data);
    }

    public static <T> Result<T> success(ResultInfo resultInfo, T data) {
        Result<T> result = new Result<>();
        result.setStatus(true);
        result.setData(data);
        result.setMessage(resultInfo.getMessage());
        result.setCode(resultInfo.getCode());
        return result;
    }

    public static <T> Result<T> error() {
        return resultData(false, ResultInfo.ERROR, null);
    }

    public static <T> Result<T> error(String message) {
        return resultData(false, 301, message, null);
    }

    public static <T> Result<T> error(@Nullable ResultInfo resultInfo,
                                      @Nullable T data) {
        return resultData(false, resultInfo, data);
    }

    public static <T> Result<T> error(@Nullable ExceptionType exceptionType,
                                      @Nullable T data) {
        return resultData(false, exceptionType, data);
    }

    public static <T> Result<T> error(ExceptionType exceptionType) {
        return resultData(false, exceptionType);
    }

    /**
     * 定义静态方法，用于返回统一结果
     *
     * @param status     结果状态 true or false
     * @param resultInfo 结果信息枚举类，封装了一些常用的状态
     * @param data       对应key的value
     * @return 返回统一结果
     */
    public static <T> Result<T> resultData(Boolean status,
                                           @Nullable ResultInfo resultInfo,
                                           @Nullable T data) {
        assert resultInfo != null;
        return getResult(status, data, resultInfo.getCode(), resultInfo.getMessage());
    }

    public static <T> Result<T> resultData(Boolean status,
                                           @Nullable ExceptionType exceptionType,
                                           @Nullable T data) {
        assert exceptionType != null;
        return getResult(status, data, exceptionType.getCode(), exceptionType.getMessage());
    }

    private static <T> Result<T> getResult(Boolean status, @Nullable T data, Integer code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setStatus(status);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> resultData(Boolean status,
                                           @Nullable Integer code,
                                           @Nullable String message,
                                           @Nullable T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setStatus(status);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    //    public static <T> Result<T> resultData(Boolean status, <T> Result<T>Info resultInfo) {
//        <T> Result<T> result = new <T> Result<T>();
//        result.setStatus(status);
//        result.setCode(resultInfo.getCode());
//        result.setMessage(resultInfo.getMessage());
//        return result;
//    }
//
    public static <T> Result<T> resultData(Boolean status, ExceptionType exceptionType) {
        Result<T> result = new Result<>();
        result.setStatus(status);
        result.setCode(exceptionType.getCode());
        result.setMessage(exceptionType.getMessage());
        return result;
    }


    public Result(boolean status, Integer code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(boolean status, ResultInfo resultInfo, T data) {
        this.status = status;
        this.code = resultInfo.getCode();
        this.message = resultInfo.getMessage();
        this.data = data;
    }

    public Result() {

    }
}
