package com.jam;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.jam.dto.UserInfoDTO;
import com.jam.entity.User;
import com.jam.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;

@SpringBootTest
class AdminSysApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
//        UserInfoDTO principal = (UserInfoDTO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        int update = userMapper.update(new User(), new LambdaUpdateWrapper<User>()
//                .eq(User::getId, principal.getUserId())
//                .set(User::getUpdateTime, new Date()));
//        System.out.println(update);
    }

}
