package com.jam.app.controller;


import com.jam.app.entity.User;
import com.jam.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jam
 * @since 2022-03-08
 */
@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/register")
    public String register(User user) {
        return userService.register(user).isStatus() ? "index" : "pages-error-404";
//        return userService.register(user).getMessage();
//        return "index";
    }
}

