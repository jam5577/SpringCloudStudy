package com.jam.controller;

import com.jam.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @program: SpringCloudStudy
 * @description: 基础controller
 * @author: Mr.Pu
 * @create: 2022-02-16 17:31
 **/
@RestController
public class BaseController {

    @Resource
    HttpServletRequest request;

    final RedisUtil redisUtil;

    @Autowired
    public BaseController(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

}
