package com.jam.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * @program: SpringCloudStudy
 * @description: 性别枚举类
 * @author: Mr.Pu
 * @create: 2022-05-27 14:50
 **/

public enum Gender {
    MALE(0, "男"),
    FEMALE(1, "女"),
    UNKNOWN(-1, "保密");

    final int code;
    @EnumValue
    final String gender;

    Gender(int code, String gender) {
        this.code = code;
        this.gender = gender;
    }


}
