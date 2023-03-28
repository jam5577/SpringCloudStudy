package com.jam.message.impl;

import com.jam.message.Message;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthResponse implements Message {

    public static final String TYPE = "AUTH_RESPONSE";

    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应提示
     */
    private String message;
    
    // ... 省略 set/get 方法
    
}