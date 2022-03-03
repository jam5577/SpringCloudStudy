package com.jam.service.impl;

import com.jam.entity.User;
import com.jam.mapper.UserMapper;
import com.jam.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jam
 * @since 2022-02-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
