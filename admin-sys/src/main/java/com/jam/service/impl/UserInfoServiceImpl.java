package com.jam.service.impl;

import com.jam.entity.UserInfo;
import com.jam.mapper.UserInfoMapper;
import com.jam.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jam
 * @since 2022-01-26
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
