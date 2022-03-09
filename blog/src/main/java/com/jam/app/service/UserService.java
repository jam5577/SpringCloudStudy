package com.jam.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jam.app.entity.User;
import com.jam.base.result.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jam
 * @since 2022-03-08
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     */
    Result<Void> register(User user);

}
