package com.jam;

import com.jam.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
