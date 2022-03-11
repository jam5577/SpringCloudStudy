package com.jam.app.handler;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jam.app.dto.UserDTO;
import com.jam.app.entity.User;
import com.jam.app.mapper.UserMapper;
import com.jam.app.utils.UserUtil;
import com.jam.base.result.Result;
import com.jam.base.result.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @program: SpringbootStudy
 * @description: 自定义成功登录返回值
 * @author: Mr.Pu
 * @create: 2022-01-21 16:24
 **/
@Slf4j
@Component
public class AdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserMapper userMapper;

    @Autowired
    public AdminAuthenticationSuccessHandler(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        UserDTO principal = (UserDTO) authentication.getPrincipal();
        log.info(JSON.toJSONString(principal));
        updateUser();
        response.setContentType("application/json;charset=UTF-8");
        String s = new ObjectMapper().writeValueAsString(Result.success(ResultInfo.LOGIN_SUCCESS, principal));
//        response.getWriter().println(s);
//        response.addCookie(new Cookie("result", s));
        response.sendRedirect("/index");
    }

    @Async
    public void updateUser() {
        UserDTO userInfo = UserUtil.getUserInfoDTO();
        System.out.println(userInfo);
        User user = User.builder()
                .ipAddress(UserUtil.getUserInfoDTO().getIpAddress())
                .updateTime(new Date())
                .build();
        userMapper.updateById(user);
    }
}
