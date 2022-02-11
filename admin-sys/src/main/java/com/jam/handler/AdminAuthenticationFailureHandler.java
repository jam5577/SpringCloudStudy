package com.jam.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jam.handler.result.Result;
import com.jam.handler.result.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
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
public class AdminAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("用户登录失败，请重新登录");
        Result result = new Result<>();
        response.setContentType("application/json;charset=UTF-8");
        String s = new ObjectMapper().writeValueAsString(result.resultData(ResultInfo.LOGIN_FAILURE,exception));
        response.getWriter().println(s);
        throw exception;
    }
}
