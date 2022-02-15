package com.jam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jam.constants.RedisPrefix;
import com.jam.entity.User;
import com.jam.enums.ExceptionType;
import com.jam.exception.LoginException;
import com.jam.mapper.UserMapper;
import com.jam.service.UserService;
import com.jam.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jam
 * @since 2022-02-13
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    private final UserMapper userMapper;

    private final StringRedisTemplate redisTemplate;

    @Resource
    private HttpSession session;

    @Resource
    private HttpServletRequest request;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, StringRedisTemplate redisTemplate) {
        this.userMapper = userMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询数据库查看是否有此用户
        User one = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
        if (Objects.isNull(one)) throw new LoginException(ExceptionType.USER_NOT_FOUND);

        //通过id查询用户的角色信息
        List<String> roleList = userMapper.findRoleById(one.getId());
        log.info("用户角色为{}", roleList);
        //通过用户名查询用户的相关信息
        String token = session.getId();
        //redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set(RedisPrefix.USER_TOKEN_KEY + token, one.getUsername(), 30, TimeUnit.MINUTES);
//        servletContext.setAttribute("adminInfo", adminInfo);
//        servletContext.setAttribute("token", token);
////        session.setAttribute("token", token);
//        HttpSession session = request.getSession();
        session.setAttribute("token", token);
        return UserUtil.conventLoginUser(one, roleList, request);
    }

    @Override
    public String generateCode() {
        return "1111";
    }
}
