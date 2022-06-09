package com.jam.spring.rabbitmq.starter.singlequeue.config;

import org.springframework.amqp.core.*;
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

    //声明2个队列，2个交换机
    //第一个交换机
    public static final String DELAY_EXCHANGE_NAME = "delay.exchange";
    //第一个部分的队列
    public static final String DELAY_QUEUE_NAME = "delay.queue";
    //第一部分的路由key
    public static final String DELAY_QUEUE_ROUTING_KEY = "delay.queue.routing";

    //死信队列的交换机
    public static final String DEAD_LETTER_EXCHANGE_NAME = "dead.letter.exchange";
    //死信队列的队列
    public static final String DEAD_LETTER_QUEUE_NAME = "dead.letter.queue";
    //死信队列的路由key
    public static final String DEAD_LETTER_QUEUE_ROUTING = "dead.letter.queue.routing";

    //声明延迟交换机
    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange(DELAY_EXCHANGE_NAME);
    }

    //声明死信交换机
    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE_NAME);
    }

    //声明延迟队列不设置ttl，并绑定交换机
    @Bean
    public Queue delayQueue() {
        Map<String, Object> map = new HashMap<>();
        // 声明队列绑定的死信交换机
        map.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE_NAME);
        // 声明队列的死信路由key
        map.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUE_ROUTING);
        // 不再声明队列的消息ttl存活时间
        //map.put("x-message-ttl", 100000);
        return QueueBuilder.durable(DELAY_QUEUE_NAME).withArguments(map).build();
    }

    //声明死信队列，用于接收各类延时消息
    @Bean
    public Queue deadQueue() {
        return new Queue(DEAD_LETTER_QUEUE_NAME);
    }

    //声明延迟队列A的绑定关系
    @Bean
    public Binding delayBingA(@Qualifier("delayQueue") Queue queue,
                              @Qualifier("delayExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUE_ROUTING_KEY);
    }

    //声明死信队列A的绑定关系
    @Bean
    public Binding deadBingA(@Qualifier("deadQueue") Queue queue,
                             @Qualifier("deadExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUE_ROUTING);
    }
}
