package com.jam.handler.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

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

    public Result<T> success(ResultInfo resultInfo){
        Result<T> result = new Result<T>();
        result.status = true;
        result.resultData(resultInfo);
        return result;
    }

    public Result<T> success(ResultInfo resultInfo, T data){
        Result<T> result = new Result<T>();
        result.status = true;
        result.resultData(resultInfo);
        result.setData(data);
        return result;
    }

    public Result<T> error(ResultInfo resultInfo){
        Result<T> result = new Result<T>();
        result.status = false;
        result.resultData(resultInfo);
        return result;
    }

    public Result<T> error(ResultInfo resultInfo, T data){
        Result<T> result = new Result<T>();
        result.status = false;
        result.resultData(resultInfo);
        result.setData(data);
        return result;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result<T> message(String message){
        this.setMessage(message);
        return this;
    }

    public Result<T> resultData(Integer code, String message){
        this.setCode(code);
        this.setMessage(message);
        return this;
    }

    public Result<T> resultData(ResultInfo resultInfo, T data){
        this.setCode(resultInfo.getCode());
        this.setMessage(resultInfo.getMessage());
        this.data=data;
        return this;
    }

    public Result<T> resultData(ResultInfo resultInfo, String key, Object value){
        this.setCode(resultInfo.getCode());
        this.setMessage(resultInfo.getMessage());
        Map<String, Object> map = new HashMap<>();
        this.data= (T) map;
        return this;
    }

    public Result<T> resultData(ResultInfo resultInfo){
        this.setCode(resultInfo.getCode());
        this.setMessage(resultInfo.getMessage());
        return this;
    }

}
