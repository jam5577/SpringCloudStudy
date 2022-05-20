package com.jam.java.io.netty.future;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @program: SpringCloudStudy
 * @description:
 * @author: Mr.Pu
 * @create: 2022-05-20 18:32
 **/
@Slf4j
public class NettyFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();

        EventLoop loop = group.next();

        Future<Integer> future = loop.submit(() -> {
            log.debug("执行计算");
            Thread.sleep(1000);
            return 70;
        });
        //同步等待结果
//        log.debug("等待结果");
//        log.debug("结果是{}", future.get());
        future.addListener(new GenericFutureListener<Future<? super Integer>>() {
            @Override
            public void operationComplete(Future<? super Integer> future) throws Exception {
                log.debug("执行计算:{}", future.getNow());
            }
        });
    }
}
