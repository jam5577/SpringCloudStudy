package com.jam.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jam.app.entity.User;
import com.jam.app.mapper.UserMapper;
import com.jam.app.service.UserService;
import com.jam.base.exception.ExceptionType;
import com.jam.base.exception.UserException;
import com.jam.base.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Result<Void> register(User user) {
        User one = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, user.getUsername()));
        if (!Objects.isNull(one)) {
            throw new UserException(ExceptionType.DUPLICATE_USERNAME);
        }
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return userMapper.insert(user) > 0 ? Result.success() : Result.error();
    }
}
