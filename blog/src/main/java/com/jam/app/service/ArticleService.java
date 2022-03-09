package com.jam.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jam.app.entity.Article;
import com.jam.app.vo.ArticleListVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jam
 * @since 2022-03-08
 */
public interface ArticleService extends IService<Article> {

    /**
     * 获取所有文章列表
     */
    List<ArticleListVO> getArticleList();

    /**
     * 获取文章相关数据
     */


    /**
     * 获取文章作者信息
     */

    /**
     * 添加或修改文章
     */

    /**
     * 上传文章图片
     */

}
