package com.jam.service;

import com.jam.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jam
 * @since 2022-02-13
 */
public interface UserService extends IService<User> {

    /**
     * 验证码生成接口
     */
    String generateCode();

}
