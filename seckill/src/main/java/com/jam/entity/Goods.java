package com.jam.entity;

import java.math.BigDecimal;
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
    @ApiModel(value="Goods对象", description="")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "商品名称")
      private String goodsName;

      @ApiModelProperty(value = "商品标题")
      private String goodsTitle;

      @ApiModelProperty(value = "商品图片")
      private String goodsImg;

      @ApiModelProperty(value = "商品描述")
      private String goodsDetail;

      @ApiModelProperty(value = "商品价格")
      private BigDecimal goodsPrice;

      @ApiModelProperty(value = "商品库存,-1表示没有限制")
      private Integer goodsStock;


}
