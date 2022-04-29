package com.jam.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022-02-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
@ApiModel(value = "User对象")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "用户名，唯一")
    private String username;

    @ApiModelProperty(value = "密码，加密后的")
    private String password;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    private String avatar;

    private Boolean gender;

    private String address;

    private String signature;

    @ApiModelProperty(value = "是否被锁定，锁定为1")
    private Boolean locked;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "最后一次登录时间")
    private Date updateTime;

    private String ipSource;

    @ApiModelProperty(value = "登录ip地址")
    private String ipAddress;

    private String email;


}
