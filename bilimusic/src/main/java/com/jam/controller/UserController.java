package com.jam.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jam.entity.User;
import com.jam.enums.Gender;
import com.jam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * (User)表控制层
 *
 * @author Mr.Pu
 * @since 2022-05-27 14:48:22
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public List<User> test(String username) {
        return userService.getBaseMapper().selectList(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username));
    }

    @GetMapping("/insert")
    public void getUser() {
        userService.getBaseMapper().insert(User.builder()
                .enabled(1)
                .gender(Gender.MALE)
                .locked(0)
                .lastLoginIp(null)
                .lastLoginTime(new Date())
                .nickname("test")
                .username("test")
                .password("test")
                .build());
    }
}

