package com.jam.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @program: SpringCloudStudy
 * @description: 用户DTO类
 * @author: Mr.Pu
 * @create: 2022-03-08 16:48
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Integer id;

    private String username;

    private String nickname;

    private String password;

    private String phone;

    private String avatar;

    private String signature;

    private Boolean gender;

    private Date createTime;

    private Date updateTime;

    private Date lastLoginTime;

    private String ipAddress;

    private String ipSource;

    private String email;

    private Boolean locked;

    private List<String> perms;

}
