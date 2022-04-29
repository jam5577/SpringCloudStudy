package com.jam.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: PureEcho
 * @description: JSON工具包
 * @author: Mr.Pu
 * @create: 2022-01-30 22:05
 **/
@Slf4j
public class JSONUtils {

    /**
     * 将Object对象转为JSON数据
     *
     * @param o
     * @return
     * @throws JsonProcessingException
     */
    public static String toJSON(Object o) {
        log.info("正常返回JSON数据");
        return JSON.toJSONString(o);
    }
}
