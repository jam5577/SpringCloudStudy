package com.jam.app.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jam.app.entity.MenuMeta;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: SpringCloudStudy
 * @description: 路由DTO
 * @author: Mr.Pu
 * @create: 2022-04-27 19:02
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class MenuDTO {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "组件名称")
    private String name;

    @ApiModelProperty(value = "组件")
    private String component;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "链接地址")
    private String path;

    @ApiModelProperty(value = "隐藏")
    private Boolean hidden;

    @ApiModelProperty(value = "meta模块")
    private MenuMeta meta;

    @ApiModelProperty(value = "重定向位置")
    private String redirect;

    @ApiModelProperty(value = "子菜单")
    private List<MenuDTO> children;
}
