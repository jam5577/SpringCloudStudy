package com.jam.app.controller;


import com.jam.app.dto.MenuDTO;
import com.jam.app.handler.Result;
import com.jam.app.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 系统菜单 前端控制器
 * </p>
 *
 * @author jam
 * @since 2022-02-13
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    private final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/getRouter")
    public Result<List<MenuDTO>> getRouter() {
        return menuService.getRouter();
    }

}

