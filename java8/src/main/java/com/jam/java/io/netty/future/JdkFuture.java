package com.jam.java.io.netty.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @program: SpringCloudStudy
 * @description:
 * @author: Mr.Pu
 * @create: 2022-05-20 18:12
 **/

@Slf4j
public class JdkFuture {
    /**
     * future是一个接口，主要用来与线程池相匹配，返回其结果
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //1.创建线程池
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 10, 120,
//                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), r -> {
//            r.run();
//            return null;
//        });
        ExecutorService executor = Executors.newFixedThreadPool(2);
        //2.提交任务
        Future<Integer> submit = executor.submit(() -> {
            log.debug("执行计算");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 50;
        });
        //3.主线程通过future获取结果
        log.debug("等待结果");
        log.debug("结果是:{}", submit.get());

    }
}
