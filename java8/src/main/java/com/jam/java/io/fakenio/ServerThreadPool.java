package com.jam.java.io.fakenio;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @program: SpringCloudStudy
 * @description: 线程池
 * @author: Mr.Pu
 * @create: 2022-05-18 21:19
 **/

public class ServerThreadPool {
    /**
     * 创建一个线程池来管理线程
     */
    private ExecutorService executorService;

    /**
     * <code>public ThreadPoolExecutor(int corePoolSize,
     * int maximumPoolSize,
     * long keepAliveTime,
     * TimeUnit unit,
     * BlockingQueue<Runnable> workQueue,
     * ThreadFactory threadFactory)</code>
     *
     * @param max   传入最大线程数
     * @param queue 传入等待队列数
     */
    public ServerThreadPool(int max, int queue) {
        executorService = new ThreadPoolExecutor(3, max, 120, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queue), new ThreadPoolTaskExecutor());
    }

    /**
     * 提供一个方法来提交任务给线程池队列暂存，等着线程池来处理
     */
    public void execute(Runnable r) {
        executorService.execute(r);
    }

}
