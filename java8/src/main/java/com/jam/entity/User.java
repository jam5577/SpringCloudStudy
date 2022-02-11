package com.jam.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.sql.Timestamp;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jam
 * @since 2022-02-10
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("sys_user")
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "用户名，唯一")
      private String username;

      @ApiModelProperty(value = "密码，加密后的")
      private String password;

      @ApiModelProperty(value = "用户昵称")
      private String nickname;

      @ApiModelProperty(value = "是否被锁定，锁定为1")
      private Boolean locked;

      @ApiModelProperty(value = "创建时间")
      private Timestamp createTime;

      @ApiModelProperty(value = "最后一次登录时间")
      private Timestamp updateTime;

      @ApiModelProperty(value = "登录ip地址")
      private String ipAddress;


}
