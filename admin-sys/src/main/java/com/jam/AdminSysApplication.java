package com.jam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(securedEnabled = true)
@SpringBootApplication
@MapperScan("com.jam.mapper")
public class AdminSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminSysApplication.class, args);
    }

}
