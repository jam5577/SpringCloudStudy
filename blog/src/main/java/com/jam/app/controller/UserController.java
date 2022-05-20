package com.jam.app.controller;


import com.jam.app.entity.User;
import com.jam.app.service.UserService;
import com.jam.app.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jam
 * @since 2022-03-08
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/register")
    public String register(User user, Model model) {
        model.addAttribute("exception", user.getUsername());
        return userService.register(user).isStatus() ? "redirect:/" : "pages-error-404";
    }

    @RequestMapping("/userList")
    public void getUserList(Model model) {
        List<UserVO> list = userService.getUserList();
        model.addAttribute("userList", list);
    }
}

