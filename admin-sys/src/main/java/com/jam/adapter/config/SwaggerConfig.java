package com.jam.adapter.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


/**
 * @program: SpringbootStudy
 * @description: swaggerConfig
 * @author: Mr.Pu
 * @create: 2021-12-30 12:11
 **/

@Configuration
@EnableKnife4j
@EnableSwagger2
public class SwaggerConfig {
    private static final String TITLE = "admin-sys系统swagger2接口文档";

    private static final String DESCRIPTION = "是基于毕设系统的升级款";

    private static final String VERSION = "v1.0";

    private static final String SERVICE_URL = "www.bilibili.com";

    private static final String LICENSE = "no license";

    private static final String LICENSE_URL = "www.bilibili.com";

    private static final String NAME = "jam";

    private static final String URL = "www.bilibili.com";

    private static final String EMAIL = "806420570@qq.com";

    //定义Docket的Bean，使用Docket完成配置
    //enable定义swagger是否开启
    @Bean
    public Docket docket(Environment environment) {

//        使用Profiles判断配置文件中的变量值
//        Profiles profiles = Profiles.of("dev","test");
        //使用environment的方法进行判断返回布尔值
//        boolean flag = environment.acceptsProfiles(profiles);

        //使用groupName进行分组，对于不同的Docket进行组名分类就可以进行协同开发
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("jam-team")
                .enable(true)
                .select()
                //RequestHandlerSelectors配置要扫描接口的方式
                //basePackage指定要扫描的包
                //any()扫描全部
                //none()不扫描
                //withClassAnnotation传入注解的类扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.jam"))
                //paths过滤路径，传入PathSelectors
                //ant()定义过滤的路径,该路径是url路径
                //regex()定义正则表达过滤
                //.paths(PathSelectors.ant("/jam/**"))
                .build();
    }

    public ApiInfo apiInfo() {
        //作者信息
        Contact DEFAULT_CONTACT = new Contact(NAME, URL, EMAIL);
        return new ApiInfo(
                TITLE,
                DESCRIPTION,
                VERSION,
                SERVICE_URL,
                DEFAULT_CONTACT,
                LICENSE,
                LICENSE_URL,
                new ArrayList<>());
    }
}
