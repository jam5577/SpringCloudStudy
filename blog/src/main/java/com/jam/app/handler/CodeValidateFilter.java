package com.jam.app.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: SpringCloudStudy
 * @description: 登录前的验证码校验过滤器
 * @author: Mr.Pu
 * @create: 2022-02-13 14:16
 **/

public class CodeValidateFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        return super.attemptAuthentication(request, response);
    }
}
