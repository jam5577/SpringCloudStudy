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
    @ApiModel(value="Tag对象", description="")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "标签名称")
      private String tagName;

      @ApiModelProperty(value = "标签key值")
      private String tagKey;

      @ApiModelProperty(value = "创建时间")
      private Date createTime;

      @ApiModelProperty(value = "更新时间")
      private Date updateTime;

      @ApiModelProperty(value = "删除时间")
      private Date deleteTime;


}
