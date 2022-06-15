package com.jam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringCloudStudy
 * @description: 测试
 * @author: Mr.Pu
 * @create: 2022-06-14 17:46
 **/

@RestController
public class DemoController {

    @GetMapping("demo")
    public String demo() {
        return "hello";
    }
}
