package com.jam.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jam.app.entity.User;
import com.jam.app.vo.UserVO;
import com.jam.base.result.Result;

import java.util.List;

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

    Result<String> login(User user);

    List<UserVO> getUserList();
}
