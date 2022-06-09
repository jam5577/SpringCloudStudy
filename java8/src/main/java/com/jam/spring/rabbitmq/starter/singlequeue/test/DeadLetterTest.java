package com.jam.spring.rabbitmq.starter.singlequeue.test;

import com.jam.spring.rabbitmq.starter.singlequeue.producer.DelayProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: SpringCloudStudy
 * @description: 测试类
 * @author: Mr.Pu
 * @create: 2022-06-09 21:23
 **/

@SpringBootTest
@RunWith(SpringRunner.class)
public class DeadLetterTest {

    @Autowired
    private DelayProducer delayProducer;

    @Test
    public void send() {
        String msg = "测试";
        delayProducer.send(msg, "10000");
    }

}
