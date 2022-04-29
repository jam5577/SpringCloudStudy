package com.jam.app.entity;

import lombok.Data;

/**
 * @program: SpringCloudStudy
 * @description: 用户登录
 * @author: Mr.Pu
 * @create: 2022-04-28 17:14
 **/

@Data
public class UserAuth {

    private String username;

    private String password;
}
