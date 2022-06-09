package com.jam.spring.rabbitmq.raw.helloworld;

import com.jam.spring.rabbitmq.raw.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: SpringCloudStudy
 * @description: 生产者
 * @author: Mr.Pu
 * @create: 2022-06-08 18:25
 **/

public class Provider {

    @Test
    public void sendMsg() throws IOException, TimeoutException {
//        //创建工厂对象连接
//        ConnectionFactory factory = new ConnectionFactory();
//        //设置访问的地址和端口
//        factory.setHost("192.168.52.129");
//        factory.setPort(5672);
//        //设置访问哪个虚拟主机
//        factory.setVirtualHost("/ems");
//        //设置访问虚拟主机的用户名和密码
//        factory.setUsername("ems");
//        factory.setPassword("ems");
//        //获取连接对象
//        Connection connection = factory.newConnection();
        Connection connection = RabbitMqUtils.getConnection();
        //获取连接中的通道
        Channel channel = connection.createChannel();
        //绑定对应队列名
        //在生产者和消费者的通道设置中，参数需要一一对应
        //参数1：队列名称
        //参数2：用来定义队列是否需要持久化
        //参数3：是否独占队列，设置后队列会与通道绑定，只有这个通道才能给这个队列发送消息
        //参数4：是否在消费完成后自动删除队列
        //参数5：额外附加参数
        channel.queueDeclare("ems", false, false, false, null);
        //发布消息
        String s = "HelloWorld";
        //参数1：交换机名称 参数2：队列名称 参数3：传递消息额外设置 参数4：消息具体内容
        for (int i = 0; i < 10; i++) {
//            channel.basicPublish("", "ems", null, s.getBytes());
            channel.basicPublish("", "ems", MessageProperties.PERSISTENT_TEXT_PLAIN, s.getBytes());

        }
        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
