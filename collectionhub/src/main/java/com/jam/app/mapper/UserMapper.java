package com.jam.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jam.app.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jam
 * @since 2022-02-16
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
