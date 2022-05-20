package com.jam.java.io.netty.eventloop;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @program: SpringCloudStudy
 * @description:
 * @author: Mr.Pu
 * @create: 2022-05-20 15:43
 **/

public class EventLoopServer {
    public static void main(String[] args) {
        //细分2：创建一个独立的EventLoopGroup，原因是在handler执行中，worker和handler是一一绑定的，如果某一个handler
        //耗时太长会导致整个任务执行的太久，就需要进行任务细分
        EventLoopGroup group = new DefaultEventLoopGroup();

        //1.启动器，负责组装netty组件，启动服务器
        new ServerBootstrap()
                //2.包含了线程和选择器，也分为boosEventLoop，WorkerEventLoop，是一个组
                //细分1：使用boss和worker进行区分，第一个参数分配给boss，第二个分配给worker
                //      boss负责accept事件，worker负责读写事件
                .group(new NioEventLoopGroup(), new NioEventLoopGroup(2))
                //3.选择服务器的实现NioServerSocketChannel，实现了ServerSocketChannel
                .channel(NioServerSocketChannel.class)
                //4.boos负责处理连接，worker(也就是child)负责处理读写，决定了child执行哪些操作(使用handler区分)
                .childHandler(
                        //5.代表和客户端进行数据读写的通道，这里是初始化用于添加别的handler
                        new ChannelInitializer<NioSocketChannel>() {
                            @Override
                            protected void initChannel(NioSocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new LoggingHandler(LogLevel.DEBUG));
                                //6.添加具体handler，stringDecoder是将byteBuff转换为字符串
                                ch.pipeline().addLast(new StringDecoder());
                                //这里是添加自定义handler
                                //使用这样的方法可以定义此handler是由哪个group进行处理，这样就可以使不同handler使用不同线程处理
                                ch.pipeline().addLast(group, "handler", new ChannelInboundHandlerAdapter() {
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
