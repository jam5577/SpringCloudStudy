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
    @ApiModel(value="Comment对象", description="")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty(value = "用户id")
      private Integer userId;

      @ApiModelProperty(value = "回复用户id")
      private Integer replyId;

      @ApiModelProperty(value = "文章id")
      private Integer articleId;

      @ApiModelProperty(value = "评论内容")
      private String commentContent;

      @ApiModelProperty(value = "父评论id")
      private Integer parentId;

      @ApiModelProperty(value = "创建时间")
      private Date createTime;

      @ApiModelProperty(value = "是否被删除")
      private Boolean isDeleted;


}
