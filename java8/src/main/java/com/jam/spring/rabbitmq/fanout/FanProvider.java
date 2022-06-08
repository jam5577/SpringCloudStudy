package com.jam.spring.rabbitmq.fanout;

import com.jam.spring.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: SpringCloudStudy
 * @description: 广播模式的生产者
 * @author: Mr.Pu
 * @create: 2022-06-08 23:50
 **/

public class FanProvider {

    public static void main(String[] args) throws IOException, TimeoutException {
        fan();
    }

    private static void fan() throws IOException, TimeoutException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare("logs", "fanout");
        //发送消息
        String s = "fanout send message";
        channel.basicPublish("logs", "", null, s.getBytes());
        channel.close();
        connection.close();
    }
}
