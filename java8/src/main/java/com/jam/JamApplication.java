package com.jam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: SpringCloudStudy
 * @description: 主动启动类
 * @author: Mr.Pu
 * @create: 2022-02-10 15:59
 **/

@SpringBootApplication
@MapperScan("com.jam.mapper")
@EnableSwagger2
//@EnableKnife4j
public class JamApplication {
    public static void main(String[] args) {
        SpringApplication.run(JamApplication.class,args);
    }
}
