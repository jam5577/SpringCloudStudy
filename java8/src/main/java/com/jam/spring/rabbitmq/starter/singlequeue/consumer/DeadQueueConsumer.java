package com.jam.spring.rabbitmq.starter.singlequeue.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.jam.spring.rabbitmq.starter.singlequeue.config.RabbitMqConfig.DEAD_LETTER_QUEUE_NAME;

/**
 * @program: SpringCloudStudy
 * @description: 死信队列的消费者
 * @author: Mr.Pu
 * @create: 2022-06-09 21:16
 **/

@Slf4j
@Component
public class DeadQueueConsumer {

    //监听死信队列
    @RabbitListener(queues = DEAD_LETTER_QUEUE_NAME)
    public void receiveA(Message message, Channel channel) {
        //获取消息
        String msg = new String(message.getBody());
        //记录日志
        log.info("当前时间:{},死信队列收到消息:{}", LocalDateTime.now(), msg);
    }
}
