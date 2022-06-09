package com.jam.spring.rabbitmq.starter.slots.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.jam.spring.rabbitmq.starter.multiplequeue.config.RabbitMqConfig.DELAY_EXCHANGE_NAME;
import static com.jam.spring.rabbitmq.starter.singlequeue.config.RabbitMqConfig.DELAY_QUEUE_ROUTING_KEY;

/**
 * @program: SpringCloudStudy
 * @description: 延迟队列的生产者
 * @author: Mr.Pu
 * @create: 2022-06-09 21:13
 **/

@Component
public class DelayProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message, Integer delay) {
        rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUE_ROUTING_KEY, message,
                msg -> {
                    //设置消息的到期时间
                    msg.getMessageProperties().setDelay(delay);
                    return msg;
                });
    }
}
