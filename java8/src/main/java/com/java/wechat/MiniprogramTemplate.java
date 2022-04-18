package com.java.wechat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @program: SpringCloudStudy
 * @description:
 * @author: Mr.Pu
 * @create: 2022-04-16 12:40
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MiniprogramTemplate {

    private String touser;

    private Map<String, Object> mp_template_msg;

}
