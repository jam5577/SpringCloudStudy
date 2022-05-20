package com.jam.app.service.impl;

import com.jam.app.entity.Tag;
import com.jam.app.mapper.TagMapper;
import com.jam.app.service.TagService;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

}
