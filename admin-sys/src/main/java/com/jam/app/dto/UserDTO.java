package com.jam.app.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @program: SpringCloudStudy
 * @description: 用户DTO对象
 * @author: Mr.Pu
 * @create: 2022-01-26 21:09
 **/
@Data
@Builder
public class UserDTO {

    private Integer userId;

    private String username;

    private String password;

    private String nickname;
    /**
     * 是否被锁定，锁定为1
     */
    private Boolean locked;

    private Date createTime;

    private Date updateTime;

    private String ipAddress;

    private String ipSource;

    private List<String> roleList;

    private String avatar;

    private String signature;

    private Boolean gender;

    private String address;

    private String email;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return this.roleList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
