package com.jam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jam.dto.UserInfoDTO;
import com.jam.entity.User;
import com.jam.entity.UserInfo;
import com.jam.mapper.UserInfoMapper;
import com.jam.mapper.UserMapper;
import com.jam.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jam.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jam
 * @since 2022-01-26
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Resource
    private HttpServletRequest request;

    @Autowired
    private ServletContext servletContext;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //通过用户名查询用户是否存在
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if(Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户不存在");
        }
        //通过id查询用户的角色信息
        List<String> roleList = userMapper.findRoleById(user.getId());
        log.info("用户角色为{}",roleList);
        //通过用户名查询用户的相关信息
        UserInfo userInfo = userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getUserId,user.getId()));
        servletContext.setAttribute("userInfo",userInfo);
        return UserUtil.conventLoginUser(user,userInfo,roleList,request);
    }
}
