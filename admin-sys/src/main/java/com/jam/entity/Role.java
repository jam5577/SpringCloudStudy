package com.jam.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-01-26
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("sys_role")
@ApiModel(value="Role对象", description="")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "用户角色id")
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty(value = "用户角色名称")
      private String roleName;

      @ApiModelProperty(value = "用户角色关键字")
      private String roleKey;


}
