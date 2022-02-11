package com.jam.enums;

import lombok.Getter;

/**
 * @program: SpringCloudStudy
 * @description: 枚举类
 * @author: Mr.Pu
 * @create: 2022-01-25 23:54
 **/
@Getter
public enum  OptDateEnum {

    NONE(0,"不操作"),
    THREE_DAY(259200,"三天时间"),
    FIVE_DAY(432000,"五天时间"),
    SEVEN_DAY(604800,"七天时间");

    private Integer timestamp;
    private String desc;

    OptDateEnum(Integer timestamp, String desc) {
        this.timestamp = timestamp;
        this.desc = desc;
    }
}
