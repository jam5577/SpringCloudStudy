package com.jam.spring.rabbitmq.raw.helloworld;

import com.jam.spring.rabbitmq.raw.utils.RabbitMqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: SpringCloudStudy
 * @description: 消费者
 * @author: Mr.Pu
 * @create: 2022-06-08 18:43
 **/

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        consume();
    }

    public static void consume() throws IOException, TimeoutException {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("192.168.52.129");
//        factory.setPort(5672);
//        factory.setVirtualHost("/ems");
//        factory.setUsername("ems");
//        factory.setPassword("ems");
//        Connection connection = factory.newConnection();
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("ems", false, false, false, null);
        //消费
        //参数1：队列名称
        //参数2：开启消息自动确认机制
        //参数3：消费消息时的回调接口
        channel.basicConsume("ems", true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("[" + System.currentTimeMillis() / 1000 + "]" + "Consumer String: " + new String(body));
            }
        });
//        channel.close();
//        connection.close();
    }
}
