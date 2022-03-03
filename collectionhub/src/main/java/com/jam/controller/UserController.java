package com.jam.controller;


import cn.hutool.core.util.RandomUtil;
import com.jam.base.constants.Prefix;
import com.jam.base.result.Result;
import com.jam.utils.RedisUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jam
 * @since 2022-02-16
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    public UserController(RedisUtil redisUtil) {
        super(redisUtil);
    }

    @GetMapping("/login")
    public String login(){
        String code = Prefix.CODE_PREFIX + RandomUtil.randomNumbers(4);
        while (redisUtil.hasKey(code)){
            code = Prefix.CODE_PREFIX + RandomUtil.randomNumbers(4);
        }
        String ticket = RandomUtil.randomNumbers(32);
        redisUtil.set(Prefix.TICKET_PREFIX , ticket);
        request.setAttribute(Prefix.REQUEST_CODE,code);
        request.setAttribute(Prefix.REQUEST_TICKET,ticket);

        return "login";
    }

    @ResponseBody
    @GetMapping("/login-check")
    public Result loginCheck(){

        //TODO 登录逻辑

        return Result.success();
    }

}

