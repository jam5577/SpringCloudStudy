package com.jam.base;

import com.jam.base.result.Result;
import com.jam.base.result.ResultInfo;
import com.jam.utils.JSONUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;
import java.util.Objects;

/**
 * @program: SpringCloudStudy
 * @description: 统一返回结果拦截器
 * @author: Mr.Pu
 * @create: 2022-02-12 23:15
 **/
@ControllerAdvice(basePackages = "com.jam")
public class ResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(o instanceof String){
            return Result.resultData(true, ResultInfo.SUCCESS.getCode(),(String) o, Objects.requireNonNull(methodParameter.getMethod()).getName());
        }else if (o instanceof Result){
            return JSONUtils.toJSON(o);
        }else if (o instanceof List){
            return Result.success(ResultInfo.SUCCESS,o);
        }
        return Result.success();
    }
}
