package com.jam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: SpringCloudStudy
 * @description: 主启动类
 * @author: Mr.Pu
 * @create: 2022-02-10 22:38
 **/
@SpringBootApplication
@MapperScan("com.jam.app.mapper")
public class ExcelApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExcelApplication.class, args);
    }
}
