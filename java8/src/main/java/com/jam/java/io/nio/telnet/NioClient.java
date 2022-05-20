package com.jam.java.io.nio.telnet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @program: SpringCloudStudy
 * @description: 客户端实现
 * @author: Mr.Pu
 * @create: 2022-05-19 16:54
 **/

public class NioClient {

    public static void main(String[] args) throws IOException {
        //获取通道
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9000));
        //切换为非阻塞模式
        channel.configureBlocking(false);
        //分配指定缓冲区大小
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //通道写入数据，进行发送
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("请输入:");
            String msg = sc.nextLine();
            buffer.put(("test ：" + msg).getBytes());
            buffer.flip();
            channel.write(buffer);
            buffer.clear();
        }


    }
}
