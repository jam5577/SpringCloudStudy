package com.jam.entity;

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
 * @since 2022-02-24
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="SecKillOrder对象", description="")
public class SecKillOrder implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "用户ID")
      private Long userId;

      @ApiModelProperty(value = "订单ID")
      private Long orderId;

      @ApiModelProperty(value = "商品ID")
      private Long goodsId;


}
