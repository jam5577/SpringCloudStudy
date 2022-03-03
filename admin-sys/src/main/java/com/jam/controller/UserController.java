package com.jam.controller;


import com.jam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jam
 * @since 2022-02-13
 */
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/code")
    public String code(){
        return userService.generateCode();
    }

    @GetMapping("/doc.html")
    public String doc(){
        return "doc.html";
    }
}

