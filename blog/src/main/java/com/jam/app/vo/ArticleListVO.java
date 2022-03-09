package com.jam.app.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @program: SpringCloudStudy
 * @description: 文章列表VO
 * @author: Mr.Pu
 * @create: 2022-03-08 17:04
 **/

@Data
public class ArticleListVO {

    private String title;

    private String username;

    private List<String> tagName;

    private String avatar;

    private String text;

    private Date updateTime;

}
