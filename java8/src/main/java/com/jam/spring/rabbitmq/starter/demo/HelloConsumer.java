package com.jam.spring.rabbitmq.starter.demo;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @program: SpringCloudStudy
 * @description: starter版的consumer，使用注解开发，并将配置写在yml文件中
 * @author: Mr.Pu
 * @create: 2022-06-09 16:56
 **/

@Component
@RabbitListener(queuesToDeclare = @Queue("hello"))
public class HelloConsumer {

    @RabbitHandler
    public void receive(String message) {
        System.out.println("message:" + message);
    }

}
