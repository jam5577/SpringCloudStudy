package com.jam.java.io.netty.handler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: SpringCloudStudy
 * @description:
 * @author: Mr.Pu
 * @create: 2022-05-20 18:44
 **/
@Slf4j
public class NettyPipeline {
    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        //1.通过channel拿到pipeline
                        ChannelPipeline pipeline = ch.pipeline();
                        //2.添加处理器并不是直接加在最末尾，而是 head -> something -> tail
                        //这里的顺序是head -> h1 -> h2 -> h3 -> tail
                        pipeline.addLast("h1", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("h1");
                                //在入栈处理器中channelRead方法会将此处的msg传给下一个in方法，会按照顺序传下去
                                super.channelRead(ctx, msg);
                            }
                        });
                        pipeline.addLast("h2", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("h2");
                                super.channelRead(ctx, msg);
                            }
                        });
                        pipeline.addLast("h3", new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                log.debug("h3");
                                super.channelRead(ctx, msg);
                                //想要执行下面的out方法，需要调用ch的writeAndFlush方法
                                //不能调用ctx中的同名方法，因为ctx中的方法会从当前的in方法链表位置往前寻找一个out方法并执行
                                ch.writeAndFlush(ctx.alloc().buffer().writeBytes("test".getBytes()));
                            }
                        });
                        //出栈的顺序会反过来，如果有数据传入，则整个执行顺序为1 2 3 6 5 4
                        //在in方法中需要向channel写入数据并刷新才能成功执行out方法
                        pipeline.addLast("h4", new ChannelOutboundHandlerAdapter() {
                            @Override
                            public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
                                log.debug("h4");
                                super.write(ctx, msg, promise);
                            }
                        });
                    }
                }).bind(8000);
    }
}
