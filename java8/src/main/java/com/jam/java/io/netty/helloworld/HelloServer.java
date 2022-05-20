package com.jam.java.io.netty.helloworld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @program: SpringCloudStudy
 * @description: netty第一个程序
 * @author: Mr.Pu
 * @create: 2022-05-20 14:37
 **/

public class HelloServer {
    public static void main(String[] args) {
        //1.启动器，负责组装netty组件，启动服务器
        new ServerBootstrap()
                //2.包含了线程和选择器，也分为boosEventLoop，WorkerEventLoop，是一个组
                .group(new NioEventLoopGroup())
                //3.选择服务器的实现NioServerSocketChannel，实现了ServerSocketChannel
                .channel(NioServerSocketChannel.class)
                //4.boos负责处理连接，worker(也就是child)负责处理读写，决定了child执行哪些操作(使用handler区分)
                .childHandler(
                        //5.代表和客户端进行数据读写的通道，这里是初始化用于添加别的handler
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                //6.添加具体handler，stringDecoder是将byteBuff转换为字符串
                                ch.pipeline().addLast(new StringDecoder());
                                //这里是添加自定义handler
                                ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                    //读事件方法
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println(msg);
                                    }
                                });
                            }
                        }).bind(9000);
    }
}
