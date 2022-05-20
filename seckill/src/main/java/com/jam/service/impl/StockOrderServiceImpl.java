package com.jam.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jam.seckill.entity.StockOrder;
import com.jam.seckill.mapper.StockOrderMapper;
import com.jam.seckill.service.StockOrderService;
import org.springframework.stereotype.Service;

/**
 * (StockOrder)表服务实现类
 *
 * @author Mr.Pu
 * @since 2022-05-16 00:29:11
 */
@Service("stockOrderService")
public class StockOrderServiceImpl extends ServiceImpl<StockOrderMapper, StockOrder> implements StockOrderService {

}

