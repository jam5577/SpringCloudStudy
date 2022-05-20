package com.jam.app.entity;

import java.util.Date;
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
 * @since 2022-03-08
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="Menu对象", description="")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "目录路径")
      private String path;

      @ApiModelProperty(value = "目录名称")
      private String name;

      @ApiModelProperty(value = "图标")
      private String icon;

      @ApiModelProperty(value = "父目录id，无则为空")
      private Integer parentId;

      @ApiModelProperty(value = "是否禁用")
      private Boolean isDisable;

      @ApiModelProperty(value = "创建时间")
      private Date createTime;

      @ApiModelProperty(value = "更新时间")
      private Date updateTime;


}
