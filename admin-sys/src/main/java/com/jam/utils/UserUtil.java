package com.jam.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jam.dto.UserInfoDTO;
import com.jam.entity.User;
import com.jam.entity.UserInfo;
import com.jam.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @program: SpringCloudStudy
 * @description: 用户工具包
 * @author: Mr.Pu
 * @create: 2022-01-26 22:37
 **/
public class UserUtil {

    /**
     * 封装用户信息并返回
     */
    public static UserInfoDTO conventLoginUser(User user, UserInfo userInfo, List roleList, HttpServletRequest request){
        String ipAddr = IpUtil.getIpAddr(request);
        String ipSource = IpUtil.getIpSource(ipAddr);
        return UserInfoDTO.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .locked(user.getLocked())
                .createTime(userInfo.getCreateTime())
                .updateTime(userInfo.getUpdateTime())
                .ipAddress(ipAddr)
                .ipSource(ipSource)
                .roleList(roleList)
                .avatar(userInfo.getAvatar())
                .signature(userInfo.getSignature())
                .gender(userInfo.getGender())
                .address(userInfo.getAddress())
                .email(userInfo.getEmail())
                .build();
    }

    public static UserInfoDTO conventLoginUser(User user, UserInfo userInfo){
        return UserInfoDTO.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .locked(user.getLocked())
                .createTime(userInfo.getCreateTime())
                .updateTime(userInfo.getUpdateTime())
                .avatar(userInfo.getAvatar())
                .signature(userInfo.getSignature())
                .gender(userInfo.getGender())
                .address(userInfo.getAddress())
                .email(userInfo.getEmail())
                .build();
    }

    /**
     * 获取用户信息并返回
     */
    public static UserInfoDTO getUserInfoDTO(){
        return (UserInfoDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

//    public UserInfoDTO getUserInfo(){
//        UserInfoDTO principal = (UserInfoDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        UserInfo userInfo = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUserId, principal.getUserId()));
//        return conventLoginUser(User.builder()
//                .id(principal.getUserId())
//                .username(principal.getUsername())
//                .password(principal.getPassword())
//                .nickname(principal.getNickname())
//                .build(), userInfo);
//    }

}
