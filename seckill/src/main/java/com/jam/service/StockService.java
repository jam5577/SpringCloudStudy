package com.jam.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jam.entity.Stock;

/**
 * (Stock)表服务接口
 *
 * @author Mr.Pu
 * @since 2022-05-15 23:56:45
 */
public interface StockService extends IService<Stock> {

    /**
     * 传入id参数进行秒杀业务
     *
     * @param id 商品id
     * @return 返回是否秒杀成功
     */
    int kill(Integer id);
}

