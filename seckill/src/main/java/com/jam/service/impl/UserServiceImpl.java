package com.jam.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jam.base.exception.ExceptionType;
import com.jam.base.result.Result;
import com.jam.entity.User;
import com.jam.mapper.UserMapper;
import com.jam.service.UserService;
import com.jam.utils.CookieUtil;
import com.jam.utils.MD5Utils;
import com.jam.utils.UUIDUtil;
import com.jam.vo.LoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jam
 * @since 2022-02-24
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Result<Void> login(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response) {
        User one = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getNickname, loginVO.getNickname()));
        if (Objects.isNull(one)){
            return Result.error(ExceptionType.LOGIN_FAILURE);
        }
//        String s = MD5Utils.encryptOutput(loginVO.getPassword());
        String s = MD5Utils.toDBPass(loginVO.getPassword());
        log.info("output:"+s);
        if (!s.equals(one.getPassword())){
            return Result.error(ExceptionType.LOGIN_FAILURE);
        }
        one.setLastLoginDate(new Date());
        userMapper.updateById(one);
        //生成cookie
        String uuid = UUIDUtil.uuid();
        request.getSession().setAttribute(uuid,one);
        CookieUtil.setCookie(request,response,"userTicket",uuid);
        return Result.success();
    }

    @Override
    public Result<String> register(LoginVO loginVO) {
        User one = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getNickname, loginVO.getNickname()));
        if (!Objects.isNull(one)){
            return Result.error(ExceptionType.BAD_REQUEST);
        }
        int insert = userMapper.insert(User.builder()
                .nickname(loginVO.getNickname())
                .registerDate(new Date())
                .password(MD5Utils.toDBPass(loginVO.getPassword()))
                .build());
        return insert > 0 ? Result.success() : Result.error();
    }
}
