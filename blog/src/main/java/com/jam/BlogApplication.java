package com.jam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: SpringCloudStudy
 * @description: 主启动类
 * @author: Mr.Pu
 * @create: 2022-03-08 13:33
 **/

@MapperScan("com.jam.app.mapper")
@SpringBootApplication
public class BlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }
}
