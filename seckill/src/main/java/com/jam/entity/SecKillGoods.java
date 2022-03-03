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
    @ApiModel(value="SecKillGoods对象", description="")
public class SecKillGoods implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "商品ID")
      private Long goodsId;

      @ApiModelProperty(value = "秒杀家")
      private BigDecimal seckillPrice;

      @ApiModelProperty(value = "库存数量")
      private Integer stockCount;

      @ApiModelProperty(value = "秒杀开始时间")
      private Timestamp startDate;

      @ApiModelProperty(value = "秒杀结束时间")
      private Timestamp endDate;


}
