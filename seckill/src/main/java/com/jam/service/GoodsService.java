package com.jam.service;

import com.jam.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jam
 * @since 2022-02-24
 */
public interface GoodsService extends IService<Goods> {

    String loginCheck(HttpSession session, Model model, String ticket);
}
