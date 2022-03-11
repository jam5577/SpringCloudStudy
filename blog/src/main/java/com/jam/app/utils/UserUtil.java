package com.jam.app.utils;

import com.jam.app.dto.UserDTO;
import com.jam.app.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
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
    public static UserDTO conventLoginUser(User user, List<String> roleList, HttpServletRequest request) {
        String ipAddr = IpUtil.getIpAddr(request);
        String ipSource = IpUtil.getIpSource(ipAddr);
        return UserDTO.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .nickname(user.getNickname())
                .locked(user.getLocked())
                .createTime(user.getCreateTime())
                .updateTime(user.getUpdateTime())
                .ipAddress(ipAddr)
                .ipSource(ipSource)
                .roleList(roleList)
                .avatar(user.getAvatar())
                .signature(user.getSignature())
                .gender(user.getGender())
                .email(user.getEmail())
                .build();
    }

//    public static UserDTO conventLoginUser(User user, UserInfo userInfo){
//        return UserDTO.builder()
//                .userId(user.getId())
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .nickname(user.getNickname())
//                .locked(user.getLocked())
//                .createTime(userInfo.getCreateTime())
//                .updateTime(userInfo.getUpdateTime())
//                .avatar(userInfo.getAvatar())
//                .signature(userInfo.getSignature())
//                .gender(userInfo.getGender())
//                .address(userInfo.getAddress())
//                .email(userInfo.getEmail())
//                .build();
//    }

    /**
     * 获取用户信息并返回
     */
    public static UserDTO getUserInfoDTO() {
        return (UserDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
