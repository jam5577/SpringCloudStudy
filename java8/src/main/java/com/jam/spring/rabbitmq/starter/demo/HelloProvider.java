package com.jam.spring.rabbitmq.starter.demo;

import com.jam.JamApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: SpringCloudStudy
 * @description: starter的生产者
 * @author: Mr.Pu
 * @create: 2022-06-09 17:02
 **/

@SpringBootTest(classes = JamApplication.class)
@RunWith(SpringRunner.class)
public class HelloProvider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //无法直接使用，必须在spring自己的测试类中使用
    @Test
    public void hello() {
        rabbitTemplate.convertAndSend("hello", new String("消费者Object"));
    }
}
