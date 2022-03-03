package com.jam.entity;

import java.math.BigDecimal;
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
 * @since 2022-02-24
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @ApiModel(value="Order对象", description="")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "用户ID")
      private Long userId;

      @ApiModelProperty(value = "商品ID")
      private Long goodsId;

      @ApiModelProperty(value = "收获地址ID")
      private Long deliveryAddrId;

      @ApiModelProperty(value = "商品名字")
      private String goodsName;

      @ApiModelProperty(value = "商品数量")
      private Integer goodsCount;

      @ApiModelProperty(value = "商品价格")
      private BigDecimal goodsPrice;

      @ApiModelProperty(value = "1 pc,2 android, 3 ios")
      private Integer orderChannel;

      @ApiModelProperty(value = "订单状态，0新建未支付，1已支付，2已发货，3已收货，4已退货，5已完成")
      private Integer status;

      @ApiModelProperty(value = "订单创建时间")
      private Timestamp createDate;

      @ApiModelProperty(value = "支付时间")
      private Timestamp payDate;


}
