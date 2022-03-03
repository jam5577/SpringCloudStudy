package com.jam.controller;


import com.jam.base.result.Result;
import com.jam.service.UserService;
import com.jam.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jam
 * @since 2022-02-24
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping({"/","/toLogin"})
    public String toLogin(){
        return "login";
    }

    /**
     * 登录功能
     */
    @PostMapping("/login")
    @ResponseBody
    public Result<Void> login(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response){
        log.info("loginVO:"+loginVO);
        return userService.login(loginVO,request,response);
    }

    @PostMapping("/register")
    public Result<String> register(LoginVO loginVO){
        return userService.register(loginVO);
    }

}

