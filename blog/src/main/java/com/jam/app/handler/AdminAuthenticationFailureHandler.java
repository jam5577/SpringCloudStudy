package com.jam.app.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jam.base.exception.ExceptionType;
import com.jam.base.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: SpringbootStudy
 * @description: 自定义登录失败返回值
 * @author: Mr.Pu
 * @create: 2022-01-21 16:39
 **/
@Slf4j
@Component
public class AdminAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        log.error("用户登录失败，请重新登录");
        response.setContentType("application/json;charset=UTF-8");
        String s = new ObjectMapper().writeValueAsString(Result.error(ExceptionType.LOGIN_FAILURE, exception));
//        response.getWriter().println(s);
        request.setAttribute("exception", s);
        response.sendError(1, s);
        throw exception;
    }
}
