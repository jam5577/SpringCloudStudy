package com.jam.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * (Stock)实体类
 *
 * @author Mr.Pu
 * @since 2022-05-15 23:56:44
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Stock implements Serializable {
    private static final long serialVersionUID = 441149708065088193L;

    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 库存
     */
    private Integer count;
    /**
     * 已售
     */
    private Integer sale;
    /**
     * 版本号，乐观锁
     */
    private Integer version;
}

