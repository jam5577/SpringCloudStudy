package com.jam.spring.rabbitmq.raw.workqueue;

import com.jam.spring.rabbitmq.raw.utils.RabbitMqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @program: SpringCloudStudy
 * @description: 工作队列消费者
 * @author: Mr.Pu
 * @create: 2022-06-08 20:24
 **/

public class WorkConsumer1 {
    public static void main(String[] args) throws IOException {
        consume();
    }

    private static void consume() throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", false, false, false, null);
        channel.basicConsume("work", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(System.currentTimeMillis() + "消费者-1:" + new String(body));
            }
        });
    }
}
