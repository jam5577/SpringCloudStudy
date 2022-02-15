package com.jam.handler.securityHandler;

import com.jam.enums.ExceptionType;
import com.jam.handler.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

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
@Component
public class AdminAccessEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(Result.error(ExceptionType.ANONYMOUS_ENTRY,authException));
    }
}
