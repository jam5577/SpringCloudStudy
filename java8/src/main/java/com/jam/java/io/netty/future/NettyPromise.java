package com.jam.java.io.netty.future;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;

import java.util.concurrent.ExecutionException;

/**
 * @program: SpringCloudStudy
 * @description:
 * @author: Mr.Pu
 * @create: 2022-05-20 18:37
 **/

public class NettyPromise {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //1.准备一个EventLoop对象
        EventLoop next = new NioEventLoopGroup().next();
        //2.可以主动创建promise
        DefaultPromise<Object> promise = new DefaultPromise<>(next);

        new Thread(() -> {
            //3.执行一个线程计算结果，计算完毕后向promise中填充结果
            System.out.println("开始计算");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            promise.setSuccess(80);
        }).start();
        //4.接收结果的线程
        System.out.println("等待结果");
        System.out.println("结果是：" + promise.get());
        //结果：
        //      等待结果
        //      开始计算
        //      结果是：80

    }
}
