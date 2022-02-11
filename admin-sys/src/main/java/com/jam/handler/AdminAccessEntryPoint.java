package com.jam.handler;

import com.jam.handler.result.Result;
import com.jam.handler.result.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: SpringbootStudy
 * @description: 自定义匿名用户访问实现类
 * @author: Mr.Pu
 * @create: 2022-01-21 17:00
 **/
@Slf4j
public class AdminAccessEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result<Object> result = new Result<>();
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(result.resultData(ResultInfo.ANONYMOUS_ENTRY,authException));
    }
}
