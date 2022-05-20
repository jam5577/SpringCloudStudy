package com.jam.app.controller;


import com.jam.app.entity.UserAuth;
import com.jam.app.handler.Result;
import com.jam.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jam
 * @since 2022-02-13
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    //模拟登陆
    @PostMapping("/login")
    public Result<String> login(UserAuth user) {
        return userService.login(user);
    }

    //携带token获取用户信息
    public Result<String> getUserInfo(@CookieValue("vue_admin_template_token") String token) {
        return Result.success();
    }
}

