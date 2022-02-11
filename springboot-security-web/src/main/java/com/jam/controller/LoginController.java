package com.jam.controller;

import com.jam.anno.OptDate;
import com.jam.enums.OptDateEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringCloudStudy
 * @description: 登录
 * @author: Mr.Pu
 * @create: 2022-01-25 23:07
 **/
@RestController
//@Secured("ROLE_admin")
public class LoginController {

    @RequestMapping("/test")
    @OptDate(OptDateEnum.THREE_DAY)
    public String test(){
        return "test";
    }
}
