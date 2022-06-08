package com.jam.spring.rabbitmq.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: SpringCloudStudy
 * @description: 工具类
 * @author: Mr.Pu
 * @create: 2022-06-08 19:11
 **/

public class RabbitMqUtils {

    private static final ConnectionFactory factory;

    static {
        //写入静态代码块，在类加载时生成，只生成一次
        factory = new ConnectionFactory();
        factory.setHost("192.168.52.129");
        factory.setPort(5672);
        factory.setVirtualHost("/ems");
        factory.setUsername("ems");
        factory.setPassword("ems");
    }

    public static Connection getConnection() {
        try {
            return factory.newConnection();
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

}
