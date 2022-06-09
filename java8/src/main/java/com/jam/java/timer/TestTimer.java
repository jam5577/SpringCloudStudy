package com.jam.java.timer;

import org.junit.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @program: SpringCloudStudy
 * @description: 定时任务
 * @author: Mr.Pu
 * @create: 2022-05-27 10:15
 **/

@Configuration
@EnableScheduling
public class TestTimer {

    @Test
    public void start() {
        Timer timer = new Timer();
        //执行定时任务
        //参数1：timerTask对象 定时任务对象
        //参数2：任务什么时候启动
        //参数3：周期任务执行时间间隔
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("一个定时任务");
            }
        }, new Date(), 1000);
    }

    /**
     * 使用cron表达式，可以使用生成器<@link>https://www.bejson.com/othertools/cron/</@link>
     */
    @Scheduled(cron = "****?")
    public void execute() {
        System.out.println("=============");
    }

}
