package com.jam.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jam.app.entity.User;
import com.jam.app.mapper.UserMapper;
import com.jam.app.service.UserService;
import com.jam.app.utils.UserUtil;
import com.jam.app.vo.UserVO;
import com.jam.base.exception.ExceptionType;
import com.jam.base.exception.UserException;
import com.jam.base.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jam
 * @since 2022-03-08
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {

    private final UserMapper userMapper;

    @Resource
    private HttpServletRequest request;

    @Resource
    HttpSession session;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Result<Void> register(User user) {
        User one = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, user.getUsername()));
        if (!Objects.isNull(one)) {
            session.setAttribute("exception", ExceptionType.DUPLICATE_USERNAME.getMessage());
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setPassword(encoder.encode(user.getPassword()));
        return userMapper.insert(user) > 0 ? Result.success() : Result.error();
    }

    @Override
    public Result<String> login(User user) {
        log.info("user:{}", user);
        User one = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername()));
        if (Objects.isNull(one)) {
            return Result.error();
        }
        return user.getPassword().equals(one.getPassword()) ? Result.success() : Result.error(ExceptionType.USERNAME_PASSWORD_NOT_MATCHING);
    }

    @Override
    public List<UserVO> getUserList() {
        List<User> userList = userMapper.selectList(new LambdaQueryWrapper<User>().select());
        List<UserVO> vos = new ArrayList<>();
        UserVO userVO = new UserVO();
        for (User user : userList) {
            BeanUtils.copyProperties(user, userVO);
            vos.add(userVO);
        }
        return vos;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User one = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username));
        if (Objects.isNull(one)) {
            request.setAttribute("exception", ExceptionType.DUPLICATE_USERNAME.getMessage());
            throw new UserException(ExceptionType.DUPLICATE_USERNAME);
//            session.setAttribute("exception", ExceptionType.DUPLICATE_USERNAME.getMessage());
        }
        List<String> roleList = userMapper.findRoleById(one.getId());
        log.info("role:{}", roleList);
        return UserUtil.conventLoginUser(one, roleList, request);
    }
}
