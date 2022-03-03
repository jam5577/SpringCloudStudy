package com.jam.service;

import com.jam.base.result.Result;
import com.jam.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jam.vo.LoginVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jam
 * @since 2022-02-24
 */
public interface UserService extends IService<User> {

    Result<Void> login(LoginVO loginVO, HttpServletRequest request, HttpServletResponse response);

    Result<String> register(LoginVO loginVO);
}
