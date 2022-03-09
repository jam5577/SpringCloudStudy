package com.jam.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: SpringCloudStudy
 * @description: 基础controller
 * @author: Mr.Pu
 * @create: 2022-03-08 23:49
 **/

@RequestMapping
@Controller
public class BaseController {

    @RequestMapping({"/", "/login"})
    public String toLogin() {
        return "pages-login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/toRegister")
    public String register() {
        return "pages-register";
    }
}
