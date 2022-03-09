package com.jam.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value = "Article对象", description = "")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键ID")
    private Integer id;

    @ApiModelProperty(value = "用户id，对应一个用户")
    private Integer uid;

    @ApiModelProperty(value = "标签id，对应多个标签")
    private Integer tagId;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "文章封面")
    private String articleAvatar;

    @ApiModelProperty(value = "浏览量")
    private Integer viewVolume;

    @ApiModelProperty(value = "收藏量")
    private Integer collectVolume;

    @ApiModelProperty(value = "点赞量")
    private Integer likeVolume;

    @ApiModelProperty(value = "不喜欢量")
    private Integer dislikeVolume;

    @ApiModelProperty(value = "投币量")
    private Integer coinVolume;

    @ApiModelProperty(value = "正文内容")
    private String text;

    @ApiModelProperty(value = "简介")
    private String introduction;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
