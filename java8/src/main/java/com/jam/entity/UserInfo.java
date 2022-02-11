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
    @TableName("sys_user_info")
@ApiModel(value="UserInfo对象", description="")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "关联用户id")
      private Integer userId;

      @ApiModelProperty(value = "头像")
      private String avatar;

      @ApiModelProperty(value = "昵称")
      private String nickname;

      @ApiModelProperty(value = "个性签名")
      private String signature;

      @ApiModelProperty(value = "性别，0为男，1为女")
      private Boolean gender;

      @ApiModelProperty(value = "所在地")
      private String address;

      @ApiModelProperty(value = "创建时间")
      private Timestamp createTime;

      @ApiModelProperty(value = "更新时间")
      private Timestamp updateTime;

      @ApiModelProperty(value = "ip来源")
      private String ipSource;

      @ApiModelProperty(value = "ip地址")
      private String ipAddress;

      @ApiModelProperty(value = "邮箱")
      private String email;


}
