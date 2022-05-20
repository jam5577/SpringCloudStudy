package com.jam.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jam.app.entity.User;
import com.jam.app.mapper.UserMapper;
import com.jam.app.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jam
 * @since 2022-02-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
