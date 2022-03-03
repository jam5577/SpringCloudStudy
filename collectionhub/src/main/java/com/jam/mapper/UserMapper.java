package com.jam.mapper;

import com.jam.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jam
 * @since 2022-02-16
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
