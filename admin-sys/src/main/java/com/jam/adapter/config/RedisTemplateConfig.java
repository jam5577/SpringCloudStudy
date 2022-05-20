package com.jam.adapter.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @program: PureEcho
 * @description: redis配置类
 * @author: Mr.Pu
 * @create: 2022-02-01 20:15
 **/

@Configuration
public class RedisTemplateConfig {

    @Autowired
    public RedisTemplateConfig(RedisTemplate redisTemplate) {
        //创建jackson序列化方式
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        //创建Object Mapper
        ObjectMapper objectMapper = new ObjectMapper();
        //允许访问对象中所有属性
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //转换json过程中保存类的信息
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.WRAPPER_ARRAY);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        //设置value的序列化规则和key的序列化规则
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        //jackson2JsonRedisSerializer就是JSON序列化规则
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        //设置hash类型key value序列化方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        //工厂创建redisTemplate对象之后再进行配置
        redisTemplate.afterPropertiesSet();
    }
}
