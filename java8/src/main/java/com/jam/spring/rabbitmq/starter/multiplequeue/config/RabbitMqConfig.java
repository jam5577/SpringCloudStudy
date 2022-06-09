package com.jam.spring.rabbitmq.starter.multiplequeue.config;

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

    //声明4个队列，2个交换机
    //第一个交换机
    public static final String DELAY_EXCHANGE_NAME = "delay.exchange";
    //第一个部分的队列
    public static final String DELAY_QUEUE_A_NAME = "delay.queue.a";
    public static final String DELAY_QUEUE_B_NAME = "delay.queue.b";
    //第一部分的路由key
    public static final String DELAY_QUEUE_A_ROUTING_KEY = "delay.queue.a.routing";
    public static final String DELAY_QUEUE_B_ROUTING_KEY = "delay.queue.b.routing";

    //死信队列的交换机
    public static final String DEAD_LETTER_EXCHANGE_NAME = "dead.letter.exchange";
    //死信队列的队列
    public static final String DEAD_LETTER_QUEUE_A_NAME = "dead.letter.queue.a";
    public static final String DEAD_LETTER_QUEUE_B_NAME = "dead.letter.queue.b";
    //死信队列的路由key
    public static final String DEAD_LETTER_QUEUE_A_ROUTING = "dead.letter.queue.delay_10s.a.routing";
    public static final String DEAD_LETTER_QUEUE_B_ROUTING = "dead.letter.queue.delay_60s.b.routing";

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

    //声明延迟队列A，延迟10s，并绑定交换机
    @Bean
    public Queue delayQueueA() {
        Map<String, Object> map = new HashMap<>();
        // 声明队列绑定的死信交换机
        map.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE_NAME);
        // 声明队列的死信路由key
        map.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUE_A_ROUTING);
        // 声明队列的消息ttl存活时间
        map.put("x-message-ttl", 100000);
        return QueueBuilder.durable(DELAY_QUEUE_A_NAME).withArguments(map).build();
    }

    //声明延迟队列B，延迟10s，并绑定交换机
    @Bean
    public Queue delayQueueB() {
        Map<String, Object> map = new HashMap<>();
        // 声明队列绑定的死信交换机
        map.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE_NAME);
        // 声明队列的死信路由key
        map.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUE_B_ROUTING);
        // 声明队列的消息ttl存活时间
        //这里可以看出，在一个队列中时间是写定了的，即便将数字提取出来写为一个static对象，
        //在每一次延时需求添加时都需要新建一个bean来完成业务
        map.put("x-message-ttl", 600000);
        return QueueBuilder.durable(DELAY_QUEUE_B_NAME).withArguments(map).build();
    }

    //声明死信队列A，用于接收超时10s的消息
    @Bean
    public Queue deadQueueA() {
        return new Queue(DEAD_LETTER_QUEUE_A_NAME);
    }

    //声明死信队列B，用于接收超时60s的消息
    @Bean
    public Queue deadQueueB() {
        return new Queue(DEAD_LETTER_QUEUE_B_NAME);
    }

    //声明延迟队列A的绑定关系
    @Bean
    public Binding delayBingA(@Qualifier("delayQueueA") Queue queue,
                              @Qualifier("delayExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUE_A_ROUTING_KEY);
    }

    //声明延迟队列B的绑定关系
    @Bean
    public Binding delayBingB(@Qualifier("delayQueueB") Queue queue,
                              @Qualifier("delayExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DELAY_QUEUE_B_ROUTING_KEY);
    }

    //声明死信队列A的绑定关系
    @Bean
    public Binding deadBingA(@Qualifier("deadQueueA") Queue queue,
                             @Qualifier("deadExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUE_A_ROUTING);
    }

    //声明死信队列B的绑定关系
    @Bean
    public Binding deadBingB(@Qualifier("deadQueueB") Queue queue,
                             @Qualifier("deadExchange") DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DEAD_LETTER_QUEUE_B_ROUTING);
    }
}
