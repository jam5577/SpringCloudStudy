package com.jam.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jam.entity.Stock;
import com.jam.mapper.StockMapper;
import com.jam.seckill.mapper.StockOrderMapper;
import com.jam.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * (Stock)表服务实现类
 *
 * @author Mr.Pu
 * @since 2022-05-15 23:56:47
 */
@Service("stockService")
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

    private final StockMapper stockMapper;
    private final com.jam.seckill.mapper.StockOrderMapper stockOrderMapper;

    @Autowired
    public StockServiceImpl(StockMapper stockMapper, StockOrderMapper stockOrderMapper) {
        this.stockMapper = stockMapper;
        this.stockOrderMapper = stockOrderMapper;
    }

    @Override
    public int kill(Integer id) {
        //校验库存
        Stock stock = stockMapper.selectById(id);
        if (stock.getSale().equals(stock.getCount())) {
            throw new RuntimeException("库存不足");
        }
        //扣除库存
        stock.setSale(stock.getSale() + 1);
        int i = stockMapper.updateById(stock);
        //创建订单
        stockOrderMapper.insert(com.jam.seckill.entity.StockOrder.builder()
                .name(stock.getName())
                .sid(stock.getId().intValue())
                .createTime(new Date())
                .build());
        return stock.getId().intValue();
    }
}

