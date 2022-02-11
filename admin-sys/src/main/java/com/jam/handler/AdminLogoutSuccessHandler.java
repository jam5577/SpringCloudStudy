package com.jam.handler;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jam.handler.result.Result;
import com.jam.handler.result.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: SpringbootStudy
 * @description: 自定义成功注销返回类
 * @author: Mr.Pu
 * @create: 2022-01-21 16:43
 **/
@Slf4j
public class AdminLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.debug("用户成功注销");
        Result result = new Result<>();
        response.setContentType("application/json;charset=UTF-8");
        String s = new ObjectMapper().writeValueAsString(result.resultData(ResultInfo.SUCCESS));
        response.getWriter().print(JSON.toJSONString(s));
        response.sendRedirect("/");
    }
}
