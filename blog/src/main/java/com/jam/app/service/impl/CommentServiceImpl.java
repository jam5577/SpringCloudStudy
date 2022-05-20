package com.jam.app.service.impl;

import com.jam.app.entity.Comment;
import com.jam.app.mapper.CommentMapper;
import com.jam.app.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jam
 * @since 2022-03-08
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
