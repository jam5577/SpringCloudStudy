package com.jam.handler;

import com.jam.handler.result.Result;
import com.jam.handler.result.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
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
public class AdminAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Result<Object> result = new Result<>();
        result.resultData(ResultInfo.FORBIDDEN,accessDeniedException);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(result);
        response.sendRedirect("/accessDenied");
    }
}
