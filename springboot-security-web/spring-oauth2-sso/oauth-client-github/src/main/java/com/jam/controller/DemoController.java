package com.jam.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Jam
 * @Date 2023/3/7
 * @Description 测试的controller
 */
@RestController
public class DemoController {

    @GetMapping("/demo")
    public DefaultOAuth2User defaultOAuth2User() {
        System.out.println("Hello, World!");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (DefaultOAuth2User) authentication.getPrincipal();
    }
}
