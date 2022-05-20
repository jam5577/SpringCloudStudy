package com.jam.controller;


import com.google.common.util.concurrent.RateLimiter;
import com.jam.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * (Stock)表控制层
 *
 * @author Mr.Pu
 * @since 2022-05-15 23:56:41
 */
@RestController
@RequestMapping("/stock")
@Slf4j
public class StockController {

    private final StockService stockService;

    /**
     * 谷歌guava的令牌桶实现，传入参数为每秒允许通过的请求数量
     * 另一个参数为自定义秒数
     */
    private final RateLimiter limiter = RateLimiter.create(10);

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    /**
     * 使用谷歌工具guava进行接口限流
     */
    public String sale(Integer id) {
        //1.没有请求到token则一直直到获取到token令牌
        log.info("time:{}", limiter.acquire());
        //2.也可以设置一个等待时间，如果在等待的时间内获取到了token令牌，则处理业务，如果没有获取到则抛弃
        if (!limiter.tryAcquire(5, TimeUnit.SECONDS)) {
            log.info("当前请求被限流，直接抛弃");
            return "抢购失败";
        }
        return limiter.toString();
    }

    @GetMapping("/kill")
    public String kill(Integer id) {
        log.info("id:{}", id);
        //尝试创建订单
        //校验库存
        //扣除库存
        //创建订单
        int kill = stockService.kill(id);
        return "" + kill;
    }
}

