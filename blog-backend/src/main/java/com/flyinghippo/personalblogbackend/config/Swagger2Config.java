package com.flyinghippo.personalblogbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration //配置类
@EnableSwagger2// 开启Swagger2的自动配置
public class Swagger2Config {

//    Swagger实例Bean是Docket，所以通过配置Docket实例来配置Swaggger。
    @Bean //配置docket以配置Swagger具体参数
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }
    //配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("FlyHippo", "https://github.com/FlyingHippoHorse", "whoarewe4u@protonmail.com");
        return new ApiInfo(
                // 标题
                "Personal-Blog",
                // 描述
                "Personal Blog @FlyHippo",
                // 版本
                "v2.0",
                // 组织链接
                "https://github.com/FlyingHippoHorse",
                // 联系人信息
                contact,
                // 许可
                "Apache 2.0 许可",
                // 许可连接
                "许可链接",
                // 扩展
                new ArrayList<>()
        );
    }

}