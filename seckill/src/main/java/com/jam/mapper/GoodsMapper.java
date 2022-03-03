package com.jam.mapper;

import com.jam.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jam.vo.GoodsVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jam
 * @since 2022-02-24
 */
@Repository
public interface GoodsMapper extends BaseMapper<Goods> {

    List<GoodsVO> findGoods();
}
