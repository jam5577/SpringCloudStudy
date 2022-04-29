package com.jam;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableKnife4j
@MapperScan("com.jam.app.mapper")
public class AdminSysApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminSysApplication.class, args);
    }
}
