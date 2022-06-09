package com.jam.spring.rabbitmq.raw.fanout;

import com.jam.spring.rabbitmq.raw.utils.RabbitMqUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @program: SpringCloudStudy
 * @description: 广播模式消费者1
 * @author: Mr.Pu
 * @create: 2022-06-08 23:56
 **/

public class FanConsumer2 {
    public static void main(String[] args) throws IOException {
        consume();
    }

    private static void consume() throws IOException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        //通道绑定交换机
        channel.exchangeDeclare("logs", "fanout");
        //获取临时队列
        String queue = channel.queueDeclare().getQueue();
        //绑定交换机和队列
        channel.queueBind(queue, "logs", "");
        channel.basicConsume(queue, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者-2： " + new String(body));
            }
        });
    }
}
