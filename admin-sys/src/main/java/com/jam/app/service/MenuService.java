package com.jam.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jam.app.dto.MenuDTO;
import com.jam.app.entity.Menu;
import com.jam.app.handler.Result;

import java.util.List;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author jam
 * @since 2022-02-13
 */
public interface MenuService extends IService<Menu> {

    Result<List<MenuDTO>> getRouter();
}
