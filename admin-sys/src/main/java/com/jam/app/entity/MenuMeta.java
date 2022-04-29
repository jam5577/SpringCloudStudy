package com.jam.app.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @program: SpringCloudStudy
 * @description: 目录的meta信息
 * @author: Mr.Pu
 * @create: 2022-04-28 14:40
 **/

@Data
@Builder
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class MenuMeta {

    //组件标题，必传
    private String title;
    //组件图标，可为空
    private String icon;
    //组件所需角色，默认为 "user"
    private List<String> roles;
    //是否不开启缓存，默认为false，也就是默认开启缓存
    private boolean noCache;
    //
    private boolean affix;
    //是否在面包屑显示，如果为true会不在面包屑显示
    private boolean breadcrumb;
    //活动目录，如果设置了路径，会在侧面高亮
    private String activeMenu;
}
