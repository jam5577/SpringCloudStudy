package com.jam.spring.rabbitmq.starter.slots.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: SpringCloudStudy
 * @description: Rabbit配置
 * @author: Mr.Pu
 * @create: 2022-06-09 20:30
 **/

@Configuration
public class RabbitMqConfig {

    //声明1个队列，1个交换机
    //第一个交换机
    public static final String DELAY_EXCHANGE_NAME = "delay.exchange";
    //第一个部分的队列
    public static final String DELAY_QUEUE_NAME = "delay.queue";
    //第一部分的路由key
    public static final String DELAY_QUEUE_ROUTING_KEY = "delay.queue.routing";

    //声明交换机的类型
    public static final String CUSTOM_EXCHANGE_TYPE = "x-delayed-message";

    //声明延迟交换机
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> map = new HashMap<>();
        //使用插件的交换机类型
        map.put("x-delayed-type", "direct");
        //使用插件的队列类型
        return new CustomExchange(DELAY_EXCHANGE_NAME, CUSTOM_EXCHANGE_TYPE, true, false, map);
    }

    //声明延迟队列不设置ttl，并绑定交换机
    @Bean
    public Queue delayQueue() {
        return new Queue(DELAY_QUEUE_NAME);
    }

    //声明延迟队列A的绑定关系
    @Bean
    public Binding delayBingA(@Qualifier("delayQueue") Queue queue,
                              @Qualifier("delayExchange") CustomExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUE_ROUTING_KEY).noargs();
    }
}
