package com.jam.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: SpringCloudStudy
 * @description: LoginVO
 * @author: Mr.Pu
 * @create: 2022-02-25 10:39
 **/

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginVO {

    private String nickname;
    private String password;
}
