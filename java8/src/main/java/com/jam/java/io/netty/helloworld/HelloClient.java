package com.jam.java.io.netty.helloworld;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.net.InetSocketAddress;

/**
 * @program: SpringCloudStudy
 * @description: 客户端起始
 * @author: Mr.Pu
 * @create: 2022-05-20 14:56
 **/

public class HelloClient {
    public static void main(String[] args) throws InterruptedException {
        //1.启动客户端
        new Bootstrap()
                //2.添加EventLoop
                .group(new NioEventLoopGroup())
                //3.选择实现的客户端类
                .channel(NioSocketChannel.class)
                //4.添加处理器类
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringDecoder());
                    }
                })
                //5.连接到服务器
                .connect(new InetSocketAddress("127.0.0.1", 9000))
                .sync()
                .channel()
                .writeAndFlush("helloWorld");
    }
}
