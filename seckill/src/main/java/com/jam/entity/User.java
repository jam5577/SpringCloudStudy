package com.jam.entity;

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
 * @since 2022-02-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "User对象")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(type = IdType.AUTO)
    private Long id;

    private String nickname;

    @ApiModelProperty(value = "MD5二次加密")
    private String password;

    private String slat;

    @ApiModelProperty(value = "头像")
    private String head;

    @ApiModelProperty(value = "注册时间")
    private Date registerDate;

    @ApiModelProperty(value = "最后一次登录时间")
    private Date lastLoginDate;

    @ApiModelProperty(value = "登录次数")
    private Integer loginCount;


}
