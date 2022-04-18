package com.jam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: SpringCloudStudy
 * @description: 封装对象
 * @author: Mr.Pu
 * @create: 2022-03-23 13:55
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Content {

    private String img;
    private String price;
    private String name;

}
