package com.jam.message.impl;

import com.jam.message.Message;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class SendToAllRequest implements Message {

    public static final String TYPE = "SEND_TO_ALL_REQUEST";

    /**
     * 消息编号
     */
    private String msgId;
    /**
     * 内容
     */
    private String content;
    
    // ... 省略 set/get 方法
     
}