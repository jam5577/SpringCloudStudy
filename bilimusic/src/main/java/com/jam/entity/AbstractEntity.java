package com.jam.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @program: SpringCloudStudy
 * @description: 抽象事例类
 * @author: Mr.Pu
 * @create: 2022-05-27 15:19
 **/

@Data
public abstract class AbstractEntity {
    private static final long serialVersionUID = -25218530522419769L;

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
