package com.jam.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jam.app.entity.Article;
import com.jam.app.mapper.ArticleMapper;
import com.jam.app.service.ArticleService;
import com.jam.app.vo.ArticleListVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jam
 * @since 2022-03-08
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public List<ArticleListVO> getArticleList() {
        List<Article> articles = articleMapper.selectList(new LambdaQueryWrapper<Article>().select());
        List<ArticleListVO> articleListVOS = new ArrayList<>();
        ArticleListVO vo = new ArticleListVO();
        for (Article article : articles) {
            BeanUtils.copyProperties(article, vo);
            articleListVOS.add(vo);
        }
        return articleListVOS;
    }
}
