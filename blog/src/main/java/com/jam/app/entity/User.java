package com.jam.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value = "User对象", description = "")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "登录用户名")
    private String username;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "个人签名")
    private String signature;

    @ApiModelProperty(value = "性别")
    private Boolean gender;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更改信息时间")
    private Date updateTime;

    @ApiModelProperty(value = "上次登录时间")
    private Date lastLoginTime;

    @ApiModelProperty(value = "ip所在地")
    private String ipAddress;

    @ApiModelProperty(value = "ip地址")
    private String ipSource;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "是否被封禁，是为1，否为0")
    private Boolean locked;


}
