package com.jam.app.handler;

import com.jam.base.exception.ExceptionType;
import com.jam.base.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: SpringbootStudy
 * @description: 自定义403无权限返回值
 * @author: Mr.Pu
 * @create: 2022-01-21 16:52
 **/
@Slf4j
@Component
public class AdminAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(Result.error(ExceptionType.FORBIDDEN));
        response.sendRedirect("/pages-error-403");
    }
}
