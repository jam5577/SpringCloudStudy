package com.jam.app.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: SpringCloudStudy
 * @description: 用户返回VO类
 * @author: Mr.Pu
 * @create: 2022-03-08 16:45
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    private String username;

    private String nickname;

    private String phone;

    private String signature;

    private String avatar;

    private Date lastLoginTime;

    private String ipAddress;

    private String email;

}
