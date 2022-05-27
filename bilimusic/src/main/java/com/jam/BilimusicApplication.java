package com.jam;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableKnife4j
@SpringBootApplication
public class BilimusicApplication {

    public static void main(String[] args) {
        SpringApplication.run(BilimusicApplication.class, args);
    }

}
