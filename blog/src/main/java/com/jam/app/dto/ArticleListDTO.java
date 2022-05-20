package com.jam.app.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @program: SpringCloudStudy
 * @description: 文章列表DTO
 * @author: Mr.Pu
 * @create: 2022-03-08 16:58
 **/

@Data
public class ArticleListDTO {

    private String title;

    private List<String> tagName;

    private String avatar;

    private String text;

    private Date updateTime;
}
