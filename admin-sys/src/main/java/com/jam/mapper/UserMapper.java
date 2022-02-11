package com.jam.mapper;

import com.jam.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jam
 * @since 2022-01-26
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    //1、新增用户
//    int createUser(User user);
    //2、根据用户查找用户角色
    List<String> findRoleById(Integer id);
    //3、删除用户

    //4、更新用户

}
