package com.jam.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jam.app.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jam
 * @since 2022-02-13
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    List<String> findRoleById(Integer id);
}
