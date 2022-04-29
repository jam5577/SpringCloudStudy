package com.jam.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jam.app.dto.MenuDTO;
import com.jam.app.entity.Menu;
import com.jam.app.entity.MenuMeta;
import com.jam.app.handler.Result;
import com.jam.app.mapper.MenuMapper;
import com.jam.app.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author jam
 * @since 2022-02-13
 */
@Service
@Slf4j
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private final MenuMapper menuMapper;

    @Autowired
    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    /**
     * 获取菜单列表
     *
     * @param menus 传入所有菜单
     * @return 返回目录列表
     */
    private Map<Long, List<Menu>> getMenuMap(List<Menu> menus) {
        return menus.stream()
                .filter(x -> Objects.nonNull(x.getPid()))
                .collect(Collectors.groupingBy(Menu::getPid));
    }

    @Override
    public Result<List<MenuDTO>> getRouter() {
        //查询出所有路由信息，返回
        List<Menu> menus = menuMapper.selectList(null);
        //获取一级菜单
        List<Menu> first = menus.stream().filter((x) -> Objects.isNull(x.getPid())).collect(Collectors.toList());
//        log.info("children:{}", children.toString());
        //获取子菜单
        Map<Long, List<Menu>> childrenMap = getMenuMap(menus);
        //将对应的title和icon信息封装到meta中
        List<MenuDTO> result = first.stream().map(item -> {
            //获取菜单排序
            List<MenuDTO> list = new ArrayList<>();
            List<Menu> children = childrenMap.get(item.getMenuId());
            if (!CollectionUtils.isEmpty(children)) {
                list = children.stream()
                        .sorted(Comparator.comparing(Menu::getMenuSort))
                        .map(menu -> MenuDTO.builder()
                                .id(menu.getMenuId().intValue())
                                .component(menu.getComponent())
                                .name(menu.getName())
                                .path(menu.getPath())
                                .meta(MenuMeta.builder()
                                        .title(menu.getTitle())
                                        .icon(menu.getIcon())
                                        .roles(Collections.singletonList("user"))
                                        .noCache(false)
                                        .affix(false)
                                        .breadcrumb(true)
                                        .activeMenu(menu.getPath())
                                        .build())
                                .build())
                        .collect(Collectors.toList());
            }
            return MenuDTO.builder()
                    .id(item.getMenuId().intValue())
                    .component(item.getComponent())
                    .name(item.getName())
                    .path(item.getPath())
                    .meta(MenuMeta.builder()
                            .title(item.getTitle())
                            .icon(item.getIcon())
                            .roles(Objects.isNull(item.getRoles()) ? Collections.singletonList("user") : Arrays.asList(item.getRoles().split(",")))
                            .noCache(false)
                            .affix(false)
                            .breadcrumb(true)
                            .activeMenu(item.getPath())
                            .build())
                    .children(list)
                    .build();
        }).collect(Collectors.toList());
        return Result.success(result);
    }
}
