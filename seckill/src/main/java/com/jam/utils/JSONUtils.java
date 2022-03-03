package com.jam.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        try {
            log.info("正常返回JSON数据");
            return new ObjectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("抛出异常，{}", String.valueOf(e));
        }
        return null;
    }
}
