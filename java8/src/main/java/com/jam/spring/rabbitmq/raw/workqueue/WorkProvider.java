package com.jam.spring.rabbitmq.raw.workqueue;

import com.jam.spring.rabbitmq.raw.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: SpringCloudStudy
 * @description: 工作队列中生产者
 * @author: Mr.Pu
 * @create: 2022-06-08 20:20
 **/

public class WorkProvider {

    public static void main(String[] args) throws IOException, TimeoutException {
        run();
    }

    private static void run() throws IOException, TimeoutException {
        Connection connection = RabbitMqUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work", false, false, false, null);
        String s = "WorkQueue";
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("", "work", null, s.getBytes());
        }
        channel.close();
        connection.close();
    }
}
