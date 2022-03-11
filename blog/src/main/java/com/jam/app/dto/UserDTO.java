package com.jam.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
public class UserDTO implements UserDetails {

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

    private List<String> roleList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roleList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
