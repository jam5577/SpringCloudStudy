package com.java.wechat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @program: SpringCloudStudy
 * @description: 推送模板
 * @author: Mr.Pu
 * @create: 2022-04-16 10:32
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PushTemplate {

    private String touser;

    private String template_id;

    private String url;

//    private Map<String, String> miniprogram;

    private Map<String, Map<String, String>> data;
}
