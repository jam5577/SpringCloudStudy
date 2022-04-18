package com.jam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: SpringCloudStudy
 * @description:
 * @author: Mr.Pu
 * @create: 2022-03-23 13:44
 **/

@Controller
public class IndexController {

    @GetMapping({"/index", "/"})
    public String index() {
        return "index";
    }
}
