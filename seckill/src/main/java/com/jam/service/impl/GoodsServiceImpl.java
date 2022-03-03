package com.jam.service.impl;

import com.jam.entity.Goods;
import com.jam.entity.User;
import com.jam.mapper.GoodsMapper;
import com.jam.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jam.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jam
 * @since 2022-02-24
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    private final GoodsMapper goodsMapper;

    @Autowired
    public GoodsServiceImpl(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    @Override
    public String loginCheck(HttpSession session, Model model, String ticket) {
        if (Objects.isNull(ticket)){
            return "login";
        }
        User user = (User) session.getAttribute(ticket);
        if (Objects.isNull(user)){
            return "login";
        }
        List<GoodsVO> goods = goodsMapper.findGoods();
        model.addAttribute("user",user);
        model.addAttribute("goods",goods);
        return "goodsList";
    }
}
