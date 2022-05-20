package com.jam.java.io.netty.eventloop;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.NettyRuntime;

import java.util.concurrent.TimeUnit;

/**
 * @program: SpringCloudStudy
 * @description:
 * @author: Mr.Pu
 * @create: 2022-05-20 15:24
 **/

public class EventLoopTest {
    public static void main(String[] args) {
        //1.创建事件循环组
        //处理：io事件，普通任务，定时任务；不传参的时候默认是创建电脑核心数*2的数量个线程
        EventLoopGroup group = new NioEventLoopGroup();
        //处理：普通任务，定时任务
//        EventLoopGroup group1 = new DefaultEventLoopGroup();
        //2.获取下一个事件循环对象
        group.next();
        //3.执行普通任务，submit和execute一样的
        group.next().submit(() -> System.out.println("测试普通任务执行"));
        //4.定时任务执行，参数包括一个runnable对象，一个delay时间，一个period间隔时间，时间单位
        group.next().scheduleAtFixedRate(() -> System.out.println("测试定时任务执行"), 0, 1, TimeUnit.SECONDS);
        System.out.println(NettyRuntime.availableProcessors());
    }
}
