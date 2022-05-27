package com.jam.entity;


import com.jam.enums.Gender;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * (User)实体类
 *
 * @author Mr.Pu
 * @since 2022-05-27 14:48:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractEntity implements Serializable {

    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private Gender gender;
    /**
     * 是否锁定，1-是，0-否
     */
    private Integer locked;
    /**
     * 是否可用，1-是，0-否
     */
    private Integer enabled;
    /**
     * 上次登录ip
     */
    private String lastLoginIp;
    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

}

