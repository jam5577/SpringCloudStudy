package com.jam.java.delayqueue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @program: SpringCloudStudy
 * @description: jdk自带的延迟队列，这是一个无界阻塞队列，
 * 该队列只有在延迟期满的时候才能从中获取元素，放入DelayQueue中的对象，必须实现Delayed接口
 * offer()添加元素
 * poll()获取并移除队列的超时元素，没有则返回空
 * take()获取并移除队列的超时元素，没有则wait当前线程，直到有元素满足超时条件时返回结果
 * @author: Mr.Pu
 * @create: 2022-06-09 20:09
 **/

public class HelloDelay implements Delayed {
    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    public void delay() {
        DelayQueue<HelloDelay> delays = new DelayQueue<>();
    }

}
