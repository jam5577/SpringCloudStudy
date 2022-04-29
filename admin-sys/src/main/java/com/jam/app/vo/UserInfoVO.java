package com.jam.app.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @program: SpringCloudStudy
 * @description: 用户信息vo表
 * @author: Mr.Pu
 * @create: 2022-02-10 21:40
 **/
@Data
@EqualsAndHashCode
public class UserInfoVO {

    @ExcelProperty(value = {"用户信息表", "关联用户id"})
    @ApiModelProperty(value = "关联用户id")
    private Integer userId;

    @ExcelProperty(value = {"用户信息表", "头像"})
    @ApiModelProperty(value = "头像")
    private String avatar;

    @ExcelProperty(value = {"用户信息表", "昵称"})
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ExcelProperty(value = {"用户信息表", "个性签名"})
    @ApiModelProperty(value = "个性签名")
    private String signature;

    @ExcelProperty(value = {"用户信息表", "性别"})
    @ApiModelProperty(value = "性别，0为男，1为女")
    private Boolean gender;

    @ExcelProperty(value = {"用户信息表", "所在地"})
    @ApiModelProperty(value = "所在地")
    private String address;

    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = {"用户信息表", "创建时间"})
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = {"用户信息表", "更新时间"})
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ExcelProperty(value = {"用户信息表", "ip来源"})
    @ApiModelProperty(value = "ip来源")
    private String ipSource;

    @ExcelProperty(value = {"用户信息表", "ip地址"})
    @ApiModelProperty(value = "ip地址")
    private String ipAddress;

    @ExcelProperty(value = {"用户信息表", "邮箱"})
    @ApiModelProperty(value = "邮箱")
    private String email;
}
