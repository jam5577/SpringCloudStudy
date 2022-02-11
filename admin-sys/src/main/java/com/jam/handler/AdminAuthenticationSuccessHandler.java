package com.jam.handler;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jam.dto.UserInfoDTO;
import com.jam.entity.User;
import com.jam.handler.result.Result;
import com.jam.handler.result.ResultInfo;
import com.jam.mapper.UserMapper;
import com.jam.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
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
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AdminAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

//    @Autowired
    private final UserMapper userMapper;

//    @Autowired
//    public AdminAuthenticationSuccessHandler(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserInfoDTO principal = (UserInfoDTO) authentication.getPrincipal();
        log.info(JSON.toJSONString(principal));
        updateUser();
//        int i = userMapper.update(new User(),new LambdaUpdateWrapper<User>()
//                                    .eq(User::getId,principal.getUserId())
//                                    .set(User::getUpdateTime,new Date()));
//        Result<Object> x = i > 0 ? new Result<>().success(ResultInfo.SUCCESS) : new Result<>().error(ResultInfo.ERROR);
//        System.out.println(x);
//        log.info("用户更改登录时间结果为{}",x);
        Result result = new Result<>();
        response.setContentType("application/json;charset=UTF-8");
        String s = new ObjectMapper().writeValueAsString(result.resultData(ResultInfo.LOGIN_SUCCESS));
        response.getWriter().println(s);
        response.sendRedirect("/index");
    }

    @Async
    public void updateUser(){
        UserInfoDTO userInfo = UserUtil.getUserInfoDTO();
        System.out.println(userInfo);
        User user = User.builder()
                .id(UserUtil.getUserInfoDTO().getUserId())
                .ipAddress(UserUtil.getUserInfoDTO().getIpAddress())
                .updateTime(new Date())
                .build();
        userMapper.updateById(user);
    }
}
