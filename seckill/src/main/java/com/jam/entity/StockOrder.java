package com.jam.seckill.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * (StockOrder)实体类
 *
 * @author Mr.Pu
 * @since 2022-05-16 00:29:11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockOrder implements Serializable {
    private static final long serialVersionUID = -98246385366779842L;

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 库存id
     */
    private Integer sid;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 创建时间
     */
    private Date createTime;
}

