package com.jam.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author jam
 * @since 2022-03-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "Perms对象", description = "")
public class Perms implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "权限名称")
    private String permsName;

    @ApiModelProperty(value = "权限url")
    private String permsUrl;

    @ApiModelProperty(value = "对应权限的key值")
    private String permsKey;


}
