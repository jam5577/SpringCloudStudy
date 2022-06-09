package com.jam.spring.rabbitmq.starter.multiplequeue.enums;

import lombok.Getter;

@Getter
public enum DelayType {

    DELAY_10S(10),
    DELAY_60S(60);

    private final int type;

    DelayType(int type) {
        this.type = type;
    }
}
