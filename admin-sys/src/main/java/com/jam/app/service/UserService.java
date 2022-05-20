package com.jam.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jam.app.entity.User;
import com.jam.app.entity.UserAuth;
import com.jam.app.handler.Result;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jam
 * @since 2022-02-13
 */

@Repository
public interface UserService extends IService<User> {

    Result<String> login(UserAuth user);
}
