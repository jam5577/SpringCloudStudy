package com.jam.service.impl;

import com.jam.entity.Order;
import com.jam.mapper.OrderMapper;
import com.jam.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jam
 * @since 2022-02-24
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
