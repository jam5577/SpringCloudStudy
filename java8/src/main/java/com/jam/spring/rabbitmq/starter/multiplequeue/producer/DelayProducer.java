package com.jam.spring.rabbitmq.starter.multiplequeue.producer;

import com.jam.spring.rabbitmq.starter.multiplequeue.enums.DelayType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.jam.spring.rabbitmq.starter.multiplequeue.config.RabbitMqConfig.*;

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

    public void send(String message, DelayType type) {
        switch (type) {
            case DELAY_10S:
                rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUE_A_ROUTING_KEY, message);
                break;
            case DELAY_60S:
                rabbitTemplate.convertAndSend(DELAY_EXCHANGE_NAME, DELAY_QUEUE_B_ROUTING_KEY, message);
                break;
            default:
        }
    }
}
