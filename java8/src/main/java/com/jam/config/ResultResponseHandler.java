package com.jam.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import com.jam.entity.User;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @program: SpringCloudStudy
 * @description: spring的返回结果拦截器，用于拦截controller的返回值并封装成一个统一返回结果类
 * @author: Mr.Pu
 * @create: 2022-02-12 11:14
 **/
@ControllerAdvice(basePackages = "com.jam.controller")
public class ResultResponseHandler implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if (methodParameter.getClass().isInstance(User.class)) return false;
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(o instanceof String){
            return JSON.toJSONString(o);
        }

        return null;
    }
}
