package com.jam.controller;


import com.jam.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jam
 * @since 2022-02-24
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    @GetMapping("/toList")
    public String goodsList(HttpSession session, Model model,@CookieValue("userTicket") String ticket){
        return goodsService.loginCheck(session,model,ticket);
    }

}

