package com.jam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jam.mapper.UserMapper;
import com.jam.entity.User;
import com.jam.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author Mr.Pu
 * @since 2022-05-27 14:48:22
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

